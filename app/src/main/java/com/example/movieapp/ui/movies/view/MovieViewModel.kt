package com.example.movieapp.ui.movies.view

data class MovieViewModel(
    val id: Int,
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val posterPath: String?,
    val isFavourite: Boolean = false,
    val overview: String,
    val voteCount: Int,
    val hasVideo: Boolean,
    val originalLanguage: String,
    val originalTitle: String?,
    val genreIds: List<Int>?,
    val backdropPath: String?,
    val adult: Boolean,
    val releaseDate: String?
)