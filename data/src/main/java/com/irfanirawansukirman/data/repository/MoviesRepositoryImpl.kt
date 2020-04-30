package com.irfanirawansukirman.data.repository

import android.util.Log
import com.irfanirawansukirman.data.database.dao.MoviesDao
import com.irfanirawansukirman.data.database.model.MoviesEntity
import com.irfanirawansukirman.data.network.base.getData
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.domain.model.Failure
import com.irfanirawansukirman.domain.model.HttpError
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.Success
import com.irfanirawansukirman.domain.model.info.MovieInfo
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper> =
        movieApi.getMovies(apiKey, sortBy).getData()

    override suspend fun getLocalMovies(): Result<List<MovieInfo>> {
        val localMovies = moviesDao.getAllMovies()
        val localMoviesMap =
            mutableListOf<MovieInfo>().apply { localMovies?.forEach { add(it.mapToDomainModel()) } }
        return if (localMovies.isNullOrEmpty()) {
            Failure(
                HttpError(
                    Throwable("${MoviesRepositoryImpl::class.java.simpleName} getLocalMovies() => Data is empty"),
                    204
                )
            )
        } else {
            Success(localMoviesMap)
        }
    }

    override suspend fun saveLocalMovies(movies: List<MovieInfo>): Result<Boolean> {
        val oldSize = moviesDao.getAllMovies()?.size ?: 0
        val moviesMap = movies.map {
            MoviesEntity(
                it.id,
                it.backdropPath,
                it.originalTitle,
                it.overview,
                it.posterPath
            )
        }
        moviesDao.insertMovie(moviesMap)
        val newSize = moviesDao.getAllMovies()?.size ?: 0

        return if (newSize > oldSize) {
            Success(true)
        } else {
            Failure(
                HttpError(
                    Throwable("${MoviesRepositoryImpl::class.java.simpleName} saveLocalMovies() => Process is failed"),
                    204
                )
            )
        }
    }

    override suspend fun deleteLocalMovies() = moviesDao.deleteLocalMovies()

}