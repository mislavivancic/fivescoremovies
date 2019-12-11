package com.example.movieapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "favourite") val isFavourite: Boolean = true,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "has_video") val hasVideo: Boolean,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "original_title") val originalTitle: String?,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "release_date") val releaseDate: String?
)