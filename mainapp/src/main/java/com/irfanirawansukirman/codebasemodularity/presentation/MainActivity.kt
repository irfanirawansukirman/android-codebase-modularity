package com.irfanirawansukirman.codebasemodularity.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.irfanirawansukirman.abstraction.base.BaseActivity
import com.irfanirawansukirman.abstraction.util.Const.Code.REQUEST_CODE
import com.irfanirawansukirman.abstraction.util.Const.Navigation.MOVIE_TITLE
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_CHAT
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_MOVIE
import com.irfanirawansukirman.abstraction.util.ext.*
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.abstraction.util.state.ViewState.Status.*
import com.irfanirawansukirman.codebasemodularity.R
import com.irfanirawansukirman.codebasemodularity.databinding.ActivityMainBinding
import com.irfanirawansukirman.data.BuildConfig
import com.irfanirawansukirman.data.common.util.Connectivity
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.model.info.MovieInfo
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.irfanirawansukirman.medsocauth.FacebookAuthUtil
import com.irfanirawansukirman.medsocauth.GoogleAuthUtil
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MultiplePermissionsListener {

    private val viewModel: MainVM by viewModel()

    private val connectivity: Connectivity by inject()

    private lateinit var mainAdapter: MainAdapter
    private lateinit var callbackManager: CallbackManager
    private lateinit var facebookAuthUtil: FacebookAuthUtil
    private lateinit var googleAuthUtil: GoogleAuthUtil

    override fun loadObservers() {
        viewModel.apply {
            movieRemoteGetState.subscribe(this@MainActivity, ::renderMoviesState)
            moviesLocalSaveState.subscribe(this@MainActivity, ::showSaveMoviesState)
            moviesLocalGetState.subscribe(this@MainActivity, ::renderLocalMovies)
        }
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        initAuthUtil()
        setupMoviesAdapterAndNavigate()
        setupMovies()
        init()
    }

    override fun continuousCall() {
        // do nothing
    }

    override fun setupViewListener() {
        mViewBinding.apply {
            progress.setOnRefreshListener {
                clearMovies()
                init()
            }
            btnGoogle.setOnSingleClickListener {
                googleAuthUtil.login(this@MainActivity, REQUEST_CODE)
            }
            btnFacebook.setOnSingleClickListener {
                facebookAuthUtil.login(this@MainActivity, {
                    showProfile(it.avatar, it.name)
                }, {
                    Log.e("FACEBOOK FAILED ", it)
                })
            }
            btnLogout.setOnSingleClickListener {
                clearProfile()

                facebookAuthUtil.logout { state ->
                    if (state) showToast(this@MainActivity, "Logout facebook is successfully")
                }
                googleAuthUtil.logout(this@MainActivity) { state ->
                    if (state) showToast(this@MainActivity, "Logout google is successfully")
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    account?.let {
                        googleAuthUtil.firebaseAuthWithGoogle(this, it, { user ->
                            showProfile(user.avatar, user.name)
                        }, {
                            Log.e("FIREBASE ERROR ", it)
                        })
                    }
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("Main Activity", "Google sign in failed", e)
                }
            }
            else -> {
                callbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun bindToolbar(): Toolbar? = mViewBinding.root.findViewById(R.id.toolbar)

    override fun enableBackButton(): Boolean = false

    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
        report?.areAllPermissionsGranted()?.let {
            if (it) showToast(this, "All Access is Granted")
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        permissions: MutableList<PermissionRequest>?,
        token: PermissionToken?
    ) {
        token?.continuePermissionRequest()
    }

    private fun init() {
        if (connectivity.isNetworkAvailable()) {
            if (getLocalMoviesSize() == 0) getMovies() else getLocalMovies()
        } else {
            progress.finish()

            showToast(
                this,
                "Connection is lost"
            )

            getLocalMovies()
        }
    }

    private fun initAuthUtil() {
        if (!::callbackManager.isInitialized) {
            callbackManager = CallbackManager.Factory.create()
        }

        if (!::facebookAuthUtil.isInitialized) {
            facebookAuthUtil = FacebookAuthUtil()
        }

        if (!::googleAuthUtil.isInitialized) {
            googleAuthUtil = GoogleAuthUtil()
        }

        facebookAuthUtil.setupFacebookCallback(callbackManager)
        googleAuthUtil.setupGsoClient(this, getString(R.string.default_web_client_id))
    }

    private fun setupMoviesAdapterAndNavigate() {
        if (!::mainAdapter.isInitialized) {
            mainAdapter = MainAdapter { movie, pos ->
                navigate(if (pos % 2 == 0) TO_MOVIE else TO_CHAT) {
                    putExtra(MOVIE_TITLE, movie.originalTitle)
                }
            }
        }
    }

    private fun setupMovies() {
        mViewBinding.recyclerMain.apply {
            linearList(this@MainActivity)
            adapter = mainAdapter
        }
    }

    private fun clearMovies() {
        mainAdapter.resetMoviesList()
    }

    private fun getMovies() {
        viewModel.getMovies(BuildConfig.MOVIE_API_KEY, "popularity.desc")
    }

    private fun renderMoviesState(viewState: ViewState<MovieInfoMapper>) {
        when (viewState.status) {
            LOADING -> progress.loading()
            SUCCESS -> {
                progress.finish()

                val data = viewState.data?.movieList?.asListOfType<MoviesResult>()
                data?.let {
                    // save movies to local database
                    saveMovies(it)
                }
            }
            ERROR -> {
                progress.finish()

                viewState.error?.message?.let {
                    showToast(this, it)
                }
            }
        }
    }

    private fun saveMovies(movie: List<MoviesResult>) {
        viewModel.saveMovies(movie)
    }

    private fun showSaveMoviesState(viewState: ViewState<Boolean>) {
        when (viewState.status) {
            LOADING -> progress.loading()
            SUCCESS -> {
                progress.finish()

                val data = viewState.data
                data?.let {
                    if (it) {
                        // after success save movies to local database
                        // get local movies and show it to ui
                        getLocalMovies()
                    } else {
                        // do anything if failed saving into local database
                    }
                }
            }
            ERROR -> {
                progress.finish()

                viewState.error?.message?.let {
                    showToast(this, it)
                }
            }
        }
    }

    private fun getLocalMoviesSize() = viewModel.moviesLocalGetState.value?.data?.size ?: 0

    private fun getLocalMovies() {
        viewModel.getLocalMovies()
    }

    private fun renderLocalMovies(viewState: ViewState<List<MovieInfo>>) {
        when (viewState.status) {
            LOADING -> progress.loading()
            SUCCESS -> {
                progress.finish()

                val data = viewState.data
                data?.let {
                    // show movies to ui
                    mainAdapter.setupMoviesList(it)
                }
            }
            ERROR -> {
                progress.finish()

                viewState.error?.message?.let {
                    showToast(this, it)
                }
            }
        }
    }

    private fun <T> renderDataState(viewState: ViewState<T>, blockExecute : () -> Unit) {
        when (viewState.status) {
            LOADING -> progress.loading()
            SUCCESS -> {
                progress.finish()

                blockExecute()
            }
            ERROR -> {
                progress.finish()

                viewState.error?.message?.let {
                    showToast(this, it)
                }
            }
        }
    }

    private fun showProfile(avatar: String, name: String) {
        mViewBinding.apply {
            Glide.with(imgAvatar)
                .load(avatar)
                .circleCrop()
                .into(imgAvatar)

            txtName.text = name
        }
    }

    private fun clearProfile() {
        mViewBinding.apply {
            Glide.with(imgAvatar)
                .load("")
                .error(android.R.color.transparent)
                .into(imgAvatar)

            txtName.text = null
        }
    }

}
