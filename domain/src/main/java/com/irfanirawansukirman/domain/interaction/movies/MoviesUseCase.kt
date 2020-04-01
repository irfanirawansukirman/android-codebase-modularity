package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MoviesUseCase {
    suspend operator fun invoke(param: String): Result<MovieInfoMapper>
}