package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : MoviesUseCase {

    val a = "1b2f29d43bf2e4f3142530bc6929d341"
    val b = "popularity.desc"

    override suspend fun getMovies(apiKey: String, sortBy: String) =
        moviesRepository.getMovies(apiKey, sortBy)

}