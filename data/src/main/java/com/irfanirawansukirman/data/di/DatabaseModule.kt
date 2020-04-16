package com.irfanirawansukirman.data.di

import androidx.room.Room
import com.irfanirawansukirman.data.MOVIES_DB
import com.irfanirawansukirman.data.database.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MoviesDatabase::class.java, MOVIES_DB)
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<MoviesDatabase>().moviesDao() }
}