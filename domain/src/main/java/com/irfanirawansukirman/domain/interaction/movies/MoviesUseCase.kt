package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MoviesUseCase {
    suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper>
}