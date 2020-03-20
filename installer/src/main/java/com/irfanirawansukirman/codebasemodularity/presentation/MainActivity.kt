package com.irfanirawansukirman.codebasemodularity.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.abstraction.util.ext.asListOfType
import com.irfanirawansukirman.abstraction.util.ext.showToast
import com.irfanirawansukirman.abstraction.util.ext.subscribe
import com.irfanirawansukirman.abstraction.util.state.ConnectionLost
import com.irfanirawansukirman.abstraction.util.state.Loading
import com.irfanirawansukirman.abstraction.util.state.Success
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.codebasemodularity.R
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.model.response.MovieInfo
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private val viewModel: MainVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.uiState.subscribe(this, ::renderMovieList)
        viewModel.getMovieList()

        throw RuntimeException("Test Crash")
    }

    private fun renderMovieList(viewState: ViewState<MovieInfo>) {
        when (viewState) {
            is Loading -> {

            }
            is Success -> {
                val data = viewState.data.movieList?.asListOfType<MoviesResult>()
                data?.let {
                    showToast(this, it.toString())
                }
            }
            is Error -> {

            }
            is ConnectionLost -> {

            }
        }
    }

}
