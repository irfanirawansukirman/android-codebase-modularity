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
    fun getMoviesRemoteState(): LiveData<ViewState<MovieInfoMapper>>

    fun saveMovies(movies: List<MoviesResult>)
    fun getLocalMovies()
    fun getMoviesLocalSaveState(): LiveData<ViewState<Boolean>>
    fun getMoviesLocalState(): LiveData<ViewState<List<MovieInfo>>>
}

class MainVM(
    private val moviesUseCase: MoviesUseCase, coroutineContextProvider: CoroutineContextProvider
) : BaseVM(coroutineContextProvider), MainContract {

    private val _moviesRemoteGetState = MutableLiveData<ViewState<MovieInfoMapper>>()
    private val _moviesLocalSaveState = MutableLiveData<ViewState<Boolean>>()
    private val _moviesLocalGetState = MutableLiveData<ViewState<List<MovieInfo>>>()

    override fun getMovies(apiKey: String, sortBy: String) {
        _moviesRemoteGetState.value = ViewState.loading()
        executeCaseWithTimeout({
            moviesUseCase.getMovies(apiKey, sortBy)
                .onSuccess { _moviesRemoteGetState.value = ViewState.success(it) }
                .onFailure { _moviesRemoteGetState.value = ViewState.error(it.throwable) }
        }, {
            _moviesRemoteGetState.value = ViewState.error(it)
        }, {
            _moviesRemoteGetState.value = ViewState.error(it)
        })
    }

    override fun getMoviesRemoteState(): LiveData<ViewState<MovieInfoMapper>> =
        _moviesRemoteGetState

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

    override fun getMoviesLocalSaveState(): LiveData<ViewState<Boolean>> = _moviesLocalSaveState

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

    override fun getMoviesLocalState(): LiveData<ViewState<List<MovieInfo>>> = _moviesLocalGetState
}
