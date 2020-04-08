package com.irfanirawansukirman.codebasemodularity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.BuildConfig
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.data.common.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import kotlinx.coroutines.launch

interface MainContract {
    fun getMoviesList()
}

class MainVM(
    private val moviesUseCase: MoviesUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseVM(), MainContract {

    private val _movieInfoState = MutableLiveData<ViewState<MovieInfoMapper>>()
    val movieInfoState: LiveData<ViewState<MovieInfoMapper>>
        get() = _movieInfoState

    override fun getMoviesList() {
        _movieInfoState.value = ViewState.loading()

        viewModelScope.launch(coroutineContextProvider.main) {
            moviesUseCase.getMovies(BuildConfig.MOVIE_API_KEY, "popularity.desc")
                .onSuccess { _movieInfoState.value = ViewState.success(it) }
                .onFailure { _movieInfoState.value = ViewState.error(it.throwable) }
        }
    }
}
