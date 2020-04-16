package com.irfanirawansukirman.data.di

import com.irfanirawansukirman.data.BuildConfig
import com.irfanirawansukirman.data.network.service.LanguageApi
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.network.createApiService
import com.irfanirawansukirman.network.createOkHttpClient
import kotlinx.serialization.UnstableDefault
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {

    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    single { createOkHttpClient(get<HttpLoggingInterceptor>()) }

    single { createApiService<MovieApi>(get(), BuildConfig.BASE_URL) }
    single { createApiService<LanguageApi>(get(), BuildConfig.MOCK_BASE_URL) }
}