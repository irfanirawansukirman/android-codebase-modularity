package com.irfanirawansukirman.domain.repository

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MoviesRepository {
    suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper>
}