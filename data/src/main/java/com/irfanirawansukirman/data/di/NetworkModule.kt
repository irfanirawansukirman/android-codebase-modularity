package com.irfanirawansukirman.data.di

import com.irfanirawansukirman.data.BuildConfig
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.network.createApiService
import com.irfanirawansukirman.network.createOkHttpClient
import kotlinx.serialization.UnstableDefault
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

@UnstableDefault
val networkModule = module {
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    single { createOkHttpClient(get<HttpLoggingInterceptor>()) }

    single { createApiService<MovieApi>(get(), BuildConfig.BASE_URL) }
}