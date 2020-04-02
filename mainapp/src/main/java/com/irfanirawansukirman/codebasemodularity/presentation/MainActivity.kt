package com.irfanirawansukirman.codebasemodularity.presentation

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.irfanirawansukirman.abstraction.base.BaseActivity
import com.irfanirawansukirman.abstraction.util.Const.Navigation.MOVIE_TITLE
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_CHAT
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_MOVIE
import com.irfanirawansukirman.abstraction.util.ext.*
import com.irfanirawansukirman.abstraction.util.state.ConnectionLost
import com.irfanirawansukirman.abstraction.util.state.Loading
import com.irfanirawansukirman.abstraction.util.state.Success
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.codebasemodularity.R
import com.irfanirawansukirman.codebasemodularity.databinding.ActivityMainBinding
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MultiplePermissionsListener {

    private val viewModel: MainVM by viewModel()

    private lateinit var mainAdapter: MainAdapter

    override fun loadObservers() {
        viewModel.uiState.subscribe(this, ::renderMoviesList)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        setupMoviesAdapterAndNavigate()
        setupMoviesList()
        getMoviesList()
    }

    override fun continuousCall() {
        // do nothing
    }

    override fun setupViewListener() {

    }

    override fun bindToolbar(): Toolbar? = mViewBinding.root.findViewById(R.id.toolbar)

    override fun enableBackButton(): Boolean = false

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

    private fun getMoviesList() {
        viewModel.getMovieList()
    }

    private fun renderMoviesList(viewState: ViewState<MovieInfoMapper>) {
        when (viewState) {
            is Loading -> {

            }
            is Success -> {
                val data = viewState.data.movieList?.asListOfType<MoviesResult>()
                data?.let {
                    mainAdapter.setupMoviesList(it)
                }
            }
            is Error -> {

            }
            is ConnectionLost -> {

            }
        }
    }

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

}
