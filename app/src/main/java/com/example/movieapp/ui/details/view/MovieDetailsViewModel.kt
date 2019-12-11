package com.example.movieapp.ui.details.view

import com.example.movieapp.data.database.entity.GenreEntity

data class MovieDetailsViewModel(
    val id: Int,
    val voteCount: Int,
    val voteAverage: Double,
    val genres: MutableList<GenreEntity>?,
    val backdropPath: String?,
    val revenue: Long,
    val budget: Long,
    val runtime: Int?,
    val overview: String,
    val title: String,
    val releaseDate: String?,
    val popularity: Double,
    val posterPath: String?,
    val hasVideo: Boolean,
    val originalLanguage: String,
    val originalTitle: String?,
    val adult: Boolean
)