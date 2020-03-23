package com.irfanirawansukirman.codebasemodularity.presentation

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfanirawansukirman.abstraction.base.BaseActivity
import com.irfanirawansukirman.abstraction.util.Const.KeyParam.TEST
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_CHAT
import com.irfanirawansukirman.abstraction.util.Const.Navigation.TO_MOVIE
import com.irfanirawansukirman.abstraction.util.ext.asListOfType
import com.irfanirawansukirman.abstraction.util.ext.linearList
import com.irfanirawansukirman.abstraction.util.ext.navigate
import com.irfanirawansukirman.abstraction.util.ext.subscribe
import com.irfanirawansukirman.abstraction.util.state.ConnectionLost
import com.irfanirawansukirman.abstraction.util.state.Loading
import com.irfanirawansukirman.abstraction.util.state.Success
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.codebasemodularity.R
import com.irfanirawansukirman.codebasemodularity.databinding.ActivityMainBinding
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.model.response.MovieInfo
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainVM by viewModel()

    private lateinit var mainAdapter: MainAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun loadObservers() {
        viewModel.uiState.subscribe(this, ::renderMoviesList)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        if (!::mainAdapter.isInitialized && !::linearLayoutManager.isInitialized) {
            linearLayoutManager = LinearLayoutManager(this)
            mainAdapter = MainAdapter { movie, pos ->
                navigate(if (pos % 2 == 0) TO_MOVIE else TO_CHAT) {
                    putExtra(TEST, "Hahaha")
                }
            }
        }

        mViewBinding.recyclerMain.apply {
            linearList(this@MainActivity)
            adapter = mainAdapter
        }

        getMoviesList()
    }

    override fun continuousCall() {
        // do nothing
    }

    override fun setupViewListener() {
        mViewBinding.txtTitle.setOnClickListener {
            viewModel.getMovieList()
        }
    }

    override fun bindToolbar(): Toolbar? = mViewBinding.root.findViewById(R.id.toolbar)

    override fun enableBackButton(): Boolean = true

    private fun getMoviesList() {
        viewModel.getMovieList()
    }

    private fun renderMoviesList(viewState: ViewState<MovieInfo>) {
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

}
