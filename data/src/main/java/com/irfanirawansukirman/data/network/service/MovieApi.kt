package com.irfanirawansukirman.data.network.service

import com.irfanirawansukirman.data.network.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String
    ): Response<MoviesResponse>
}