package com.irfanirawansukirman.data.database.dao

import androidx.room.*
import com.irfanirawansukirman.data.MOVIES
import com.irfanirawansukirman.data.database.model.MoviesEntity
import com.irfanirawansukirman.data.network.model.MoviesResult

@Dao
interface MoviesDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovie(movie: MoviesEntity)
//
//    @Query("SELECT * FROM $MOVIES")
//    suspend fun getAllMovies(): List<MoviesResult>
//
//    @Query("SELECT * FROM $MOVIES WHERE name = :title LIMIT 1")
//    suspend fun getMovie(title: String): MoviesResult

}