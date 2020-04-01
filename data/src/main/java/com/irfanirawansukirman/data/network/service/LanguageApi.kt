package com.irfanirawansukirman.data.network.service

import com.irfanirawansukirman.data.network.model.LanguageResponse
import retrofit2.Response
import retrofit2.http.GET

interface LanguageApi {

    @GET("questions")
    suspend fun getLanguage(): Response<LanguageResponse>

}