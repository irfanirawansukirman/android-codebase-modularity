package com.irfanirawansukirman.domain

import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCaseImpl
import com.irfanirawansukirman.domain.repository.MoviesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesUseCaseTest {

    private val moviesRepository: MoviesRepository = mock()

    private val useCase by lazy { MoviesUseCaseImpl(moviesRepository) }

    private val apiKey = "dummyApiKey"
    private val sortBy = "dummySort"

    @Test
    fun `call getMovies from repository`() {
        runBlocking {
            useCase.getMovies(apiKey, sortBy)
            verify(moviesRepository).getMovies(apiKey, sortBy)
        }
    }

}