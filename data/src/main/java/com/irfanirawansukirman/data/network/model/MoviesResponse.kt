package com.irfanirawansukirman.data.network.model

import com.irfanirawansukirman.data.database.model.MoviesEntity
import com.irfanirawansukirman.data.network.base.DomainMapper
import com.irfanirawansukirman.data.network.base.RoomMapper
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<MoviesResult>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
) : DomainMapper<MovieInfoMapper> {
    override fun mapToDomainModel() =
        MovieInfoMapper(results)
}

@Serializable
data class MoviesResult(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
) : RoomMapper<MoviesEntity> {
    override fun mapToRoomEntity() =
        MoviesEntity(id, backdropPath, originalTitle, overview, posterPath)
}