package com.example.movieapp.domain

import com.example.movieapp.data.database.entity.GenreEntity

data class MovieDetails(
    val voteCount: Int = 0,
    val hasVideo: Boolean = false,
    val voteAverage: Double = 0.0,
    val popularity: Double = 0.0,
    val posterPath: String?,
    val originalLanguage: String = "",
    val originalTitle: String? = null,
    val genres: MutableList<GenreEntity>? = null,
    val backdropPath: String?,
    val adult: Boolean = false,
    val revenue: Long = 0,
    val budget: Long = 0,
    val runtime: Int? = 0,
    val overview: String = "",
    val tagline: String = "",
    val title: String = "",
    val id: Int = 0,
    val releaseDate: String? = null
)
