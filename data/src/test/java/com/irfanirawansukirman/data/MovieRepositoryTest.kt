package com.irfanirawansukirman.data

import com.irfanirawansukirman.data.common.util.Connectivity
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.data.repository.MoviesRepositoryImpl
import com.nhaarman.mockitokotlin2.mock

class MovieRepositoryTest {

    private val movieApi: MovieApi = mock()
    private val connectivity: Connectivity = mock()
    private val moviesRepository = MoviesRepositoryImpl(movieApi)

    private val apiKey = "dummyApiKey"
    private val sortBy = "dummySort"

//    @Test
//    fun `getMovies call API is Success`() {
//        runBlocking {
//            // given
//            whenever(connectivity.isNetworkAvailable()).thenReturn(true)
//            whenever(movieApi.getMovies(apiKey, sortBy))
//
//            // when
//            moviesRepository.getMovies(apiKey, sortBy)
//
//            // then
//            verify(movieApi, times(1)).getMovies(apiKey, sortBy)
//        }
//    }
}