package com.irfanirawansukirman.data

import com.irfanirawansukirman.data.database.dao.MoviesDao
import com.irfanirawansukirman.data.network.model.MoviesResponse
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.data.repository.MoviesRepositoryImpl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class MovieRepositoryTest {

    private val movieApi: MovieApi = mock()
    private val moviesDao: MoviesDao = mock()
    private val moviesRepository = MoviesRepositoryImpl(movieApi, moviesDao)

    private val apiKey = "dummyApiKey"
    private val sortBy = "dummySort"

    private val moviesList = listOf(
        MoviesResult(
            false,
            "",
            listOf(1),
            1,
            "",
            "",
            "",
            0.0,
            "",
            "", "",
            false,
            0.0,
            1
        )
    )
    private val moviesResponse = MoviesResponse(0, moviesList, 0, 0)

    @Test
    fun `getMovies call API is Success`() {
        runBlocking {
            // given
            whenever(
                movieApi.getMovies(
                    apiKey,
                    sortBy
                )
            ).thenReturn(Response.success(moviesResponse))

            // when
            moviesRepository.getMovies(apiKey, sortBy)

            // then
            verify(movieApi, times(1)).getMovies(apiKey, sortBy)
        }
    }
}