package com.irfanirawansukirman.codebasemodularity.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.facebook.CallbackManager
import com.irfanirawansukirman.abstraction.base.BaseActivity
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
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.irfanirawansukirman.medsocauth.FacebookAuthUtil
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

    override fun loadObservers() {
        viewModel.movieInfoState.subscribe(this, ::renderMoviesList)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        setupMoviesAdapterAndNavigate()
        setupMoviesList()
        init()
    }

    override fun continuousCall() {
        // do nothing
    }

    override fun setupViewListener() {
        if (!::callbackManager.isInitialized) {
            callbackManager = CallbackManager.Factory.create()
        }

        if (!::facebookAuthUtil.isInitialized) {
            facebookAuthUtil = FacebookAuthUtil()
        }

        facebookAuthUtil.setupFacebookCallback(callbackManager)

        mViewBinding.apply {
            progress.setOnRefreshListener {
                clearMoviesList()
                init()
            }
            btnFacebook.setOnClickListener {
                facebookAuthUtil.login(this@MainActivity, {
                    Log.d("FACEBOOK SUCCESS ", it.avatar)
                }, {
                    Log.e("FACEBOOK FAILED ", it)
                })
            }
            btnLogout.setOnClickListener {
                facebookAuthUtil.logout { state ->
                    if (state) showToast(this@MainActivity, "Logout is successfully")
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
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
            getMoviesList()
        } else {
            progress.finish()

            showToast(
                this,
                "Connection is lost"
            )
        }
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

    private fun setupMoviesList() {
        mViewBinding.recyclerMain.apply {
            linearList(this@MainActivity)
            adapter = mainAdapter
        }
    }

    private fun clearMoviesList() {
        mainAdapter.resetMoviesList()
    }

    private fun getMoviesList() {
        viewModel.getMoviesList(BuildConfig.MOVIE_API_KEY, "popularity.desc")
    }

    private fun renderMoviesList(viewState: ViewState<MovieInfoMapper>) {
        when (viewState.status) {
            LOADING -> progress.loading()
            SUCCESS -> {
                progress.finish()

                val data = viewState.data?.movieList?.asListOfType<MoviesResult>()
                data?.let {
                    mainAdapter.setupMoviesList(it)
                }
            }
            ERROR -> progress.finish()
        }
    }

}
