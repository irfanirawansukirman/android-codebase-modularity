package com.irfanirawansukirman.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.irfanirawansukirman.data.MOVIES
import com.irfanirawansukirman.data.network.base.DomainMapper
import com.irfanirawansukirman.domain.model.info.MovieInfo

@Entity(tableName = MOVIES)
data class MoviesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?
) : DomainMapper<MovieInfo> {
    override fun mapToDomainModel() =
        MovieInfo(id, backdropPath, originalTitle, overview, posterPath)
}