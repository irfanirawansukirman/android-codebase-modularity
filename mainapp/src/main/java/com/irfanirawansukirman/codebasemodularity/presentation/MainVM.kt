package com.irfanirawansukirman.codebasemodularity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.data.common.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MainContract {
    fun getMoviesList(apiKey: String, sortBy: String)
}

class MainVM(
    private val moviesUseCase: MoviesUseCase, coroutineContextProvider: CoroutineContextProvider
) : BaseVM(coroutineContextProvider), MainContract {

    private val _movieInfoState = MutableLiveData<ViewState<MovieInfoMapper>>()
    val movieInfoState: LiveData<ViewState<MovieInfoMapper>>
        get() = _movieInfoState

    override fun getMoviesList(apiKey: String, sortBy: String) {
        _movieInfoState.value = ViewState.loading()
        executeCase({
            moviesUseCase.getMovies(apiKey, sortBy)
                .onSuccess { _movieInfoState.value = ViewState.success(it) }
                .onFailure { _movieInfoState.value = ViewState.error(it.throwable) }
        }, {
            _movieInfoState.value = ViewState.error(it)
        }, {
            _movieInfoState.value = ViewState.error(it)
        })
    }
}
