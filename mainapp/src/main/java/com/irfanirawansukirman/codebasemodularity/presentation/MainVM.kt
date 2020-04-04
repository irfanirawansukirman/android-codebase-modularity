package com.irfanirawansukirman.codebasemodularity.presentation

import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MainContract {
    fun getMovieList()
}

class MainVM(private val moviesUseCase: MoviesUseCase) : BaseVM<MovieInfoMapper, MainViewEffects>(),
    MainContract {

    override fun getMovieList() = executeUseCase({
        moviesUseCase("")
            .onSuccess {
                _uiState.value = ViewState.success(it)
            }
            .onFailure {
                _uiState.value = ViewState.error(it.throwable)
            }
    }, {
        _uiState.value = ViewState.connectionLost()
    })

}