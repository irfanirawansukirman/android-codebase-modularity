package com.irfanirawansukirman.codebasemodularity.presentation

import android.util.Log
import com.irfanirawansukirman.abstraction.util.state.ConnectionLost
import com.irfanirawansukirman.abstraction.util.state.Error
import com.irfanirawansukirman.abstraction.util.state.Success
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.MovieInfo

class MainVM(private val moviesUseCase: MoviesUseCase) : BaseVM<MovieInfo, MainViewEffects>() {

    fun getMovieList() = executeUseCase({
        moviesUseCase("")
            .onSuccess {
                Log.d("DATA MOVIE ", it.movieList.toString())
                _uiState.value =
                    Success(it)
            }
            .onFailure {
                _uiState.value =
                    Error(it.throwable)
            }
    }, {
        _uiState.value = ConnectionLost()
    })

}