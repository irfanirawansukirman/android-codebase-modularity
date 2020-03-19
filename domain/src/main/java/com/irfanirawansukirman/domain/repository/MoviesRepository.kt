package com.irfanirawansukirman.domain.repository

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfo

interface MoviesRepository {
    suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfo>
}