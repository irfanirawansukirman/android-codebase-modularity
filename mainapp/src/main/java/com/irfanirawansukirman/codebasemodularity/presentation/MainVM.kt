package com.irfanirawansukirman.codebasemodularity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.data.common.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.info.MovieInfo
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MainContract {
    fun getMovies(apiKey: String, sortBy: String)

    fun saveMovies(movies: List<MoviesResult>)
    fun getLocalMovies()
}

class MainVM(
    private val moviesUseCase: MoviesUseCase, coroutineContextProvider: CoroutineContextProvider
) : BaseVM(coroutineContextProvider), MainContract {

    private val _movieRemoteGetState = MutableLiveData<ViewState<MovieInfoMapper>>()
    val movieRemoteGetState: LiveData<ViewState<MovieInfoMapper>>
        get() = _movieRemoteGetState

    private val _moviesLocalSaveState = MutableLiveData<ViewState<Boolean>>()
    val moviesLocalSaveState: LiveData<ViewState<Boolean>>
        get() = _moviesLocalSaveState

    private val _moviesLocalGetState = MutableLiveData<ViewState<List<MovieInfo>>>()
    val moviesLocalGetState: LiveData<ViewState<List<MovieInfo>>>
        get() = _moviesLocalGetState

    override fun getMovies(apiKey: String, sortBy: String) {
        _movieRemoteGetState.value = ViewState.loading()
        executeCaseWithTimeout({
            moviesUseCase.getMovies(apiKey, sortBy)
                .onSuccess { _movieRemoteGetState.value = ViewState.success(it) }
                .onFailure { _movieRemoteGetState.value = ViewState.error(it.throwable) }
        }, {
            _movieRemoteGetState.value = ViewState.error(it)
        }, {
            _movieRemoteGetState.value = ViewState.error(it)
        })
    }

    override fun saveMovies(movies: List<MoviesResult>) {
        _moviesLocalSaveState.value = ViewState.loading()
        executeCase({
            val moviesMap = movies.map {
                MovieInfo(
                    it.id,
                    it.backdropPath,
                    it.originalTitle,
                    it.overview,
                    it.posterPath
                )
            }
            moviesUseCase.apply {
                deleteLocalMovies()
                saveLocalMovies(moviesMap)
                    .onSuccess { _moviesLocalSaveState.value = ViewState.success(it) }
                    .onFailure { _moviesLocalSaveState.value = ViewState.error(it.throwable) }
            }
        }, {
            _moviesLocalSaveState.value = ViewState.error(it)
        })
    }

    override fun getLocalMovies() {
        _moviesLocalGetState.value = ViewState.loading()
        executeCase({
            moviesUseCase.getLocalMovies()
                .onSuccess { _moviesLocalGetState.value = ViewState.success(it) }
                .onFailure { _moviesLocalGetState.value = ViewState.error(it.throwable) }
        }, {
            _moviesLocalGetState.value = ViewState.error(it)
        })
    }
}
