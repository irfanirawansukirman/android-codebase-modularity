package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : MoviesUseCase {

    override suspend fun invoke(param: String) =
        moviesRepository.getMovies("1b2f29d43bf2e4f3142530bc6929d341", "popularity.desc")

}