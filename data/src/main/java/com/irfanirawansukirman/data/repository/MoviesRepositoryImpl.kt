package com.irfanirawansukirman.data.repository

import com.irfanirawansukirman.data.network.base.getData
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val movieApi: MovieApi
) : MoviesRepository {

    override suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper> {
        return movieApi.getMovies(apiKey, sortBy).getData()
    }

}