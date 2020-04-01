package com.irfanirawansukirman.data.di

import com.irfanirawansukirman.data.repository.LanguageRepositoryImpl
import com.irfanirawansukirman.data.repository.MoviesRepositoryImpl
import com.irfanirawansukirman.domain.repository.LanguageRepository
import com.irfanirawansukirman.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
    factory<LanguageRepository> { LanguageRepositoryImpl(get()) }
}