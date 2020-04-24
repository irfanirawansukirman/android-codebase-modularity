package com.irfanirawansukirman.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irfanirawansukirman.data.MOVIES
import com.irfanirawansukirman.data.database.model.MoviesEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviesEntity)

    @Query("SELECT * FROM $MOVIES")
    suspend fun getAllMovies(): List<MoviesEntity>

    @Query("SELECT * FROM $MOVIES WHERE id = :movieId LIMIT 1")
    suspend fun getMovie(movieId: Int): MoviesEntity

}