package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.info.MovieInfo
import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : MoviesUseCase {

    override suspend fun getMovies(apiKey: String, sortBy: String) =
        moviesRepository.getMovies(apiKey, sortBy)

    override suspend fun getLocalMovies(): Result<List<MovieInfo>> =
        moviesRepository.getLocalMovies()

    override suspend fun saveLocalMovies(movies: List<MovieInfo>): Result<Boolean> =
        moviesRepository.saveLocalMovies(movies)

    override suspend fun deleteLocalMovies() =
        moviesRepository.deleteLocalMovies()

}