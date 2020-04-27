package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : MoviesUseCase {

    override suspend fun getMovies(apiKey: String, sortBy: String) =
        moviesRepository.getMovies(apiKey, sortBy)

}