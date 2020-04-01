package com.irfanirawansukirman.domain.di

import com.irfanirawansukirman.domain.interaction.language.LanguageUseCase
import com.irfanirawansukirman.domain.interaction.language.LanguageUseCaseImpl
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<MoviesUseCase> { MoviesUseCaseImpl(get()) }
    factory<LanguageUseCase> { LanguageUseCaseImpl(get()) }
}