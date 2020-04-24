package com.irfanirawansukirman.data.repository

import com.irfanirawansukirman.data.database.dao.MoviesDao
import com.irfanirawansukirman.data.network.base.getData
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper> {
        val response = movieApi.getMovies(apiKey, sortBy).body()
        response?.let { data ->
            data.results?.let { movies ->
                movies.forEach { movie ->
                    moviesDao.insertMovie(movie.mapToRoomEntity())
                }
            }
        }
        return movieApi.getMovies(apiKey, sortBy).getData()
    }

}