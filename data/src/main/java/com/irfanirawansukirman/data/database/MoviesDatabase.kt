package com.irfanirawansukirman.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfanirawansukirman.data.database.dao.MoviesDao
import com.irfanirawansukirman.data.database.model.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}