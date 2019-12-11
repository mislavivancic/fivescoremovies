package com.example.movieapp.domain

import java.io.Serializable

data class Movie(
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val id: Int,
    val voteCount: Int,
    val hasVideo: Boolean,
    val popularity: Double,
    val posterPath: String?,
    val originalLanguage: String,
    val originalTitle: String?,
    val genreIds: List<Int>?,
    val backdropPath: String?,
    val adult: Boolean,
    val releaseDate: String?,
    val isFavourite: Boolean = false
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return this.id == (other as Movie).id
    }
}