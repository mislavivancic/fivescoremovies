package com.example.movieapp.data.model

import com.squareup.moshi.Json

class ApiMovie {

    @field:Json(name = "vote_count")
    val voteCount: Int = 0

    @field:Json(name = "video")
    val hasVideo: Boolean = false

    @field:Json(name = "vote_average")
    val voteAverage: Double = 0.0

    @Json(name = "popularity")
    val popularity: Double = 0.0

    @field:Json(name = "poster_path")
    val posterPath: String = ""

    @field:Json(name = "original_language")
    val originalLanguage: String = ""

    @field:Json(name = "original_title")
    val originalTitle: String? = null

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>? = null

    @field:Json(name = "backdrop_path")
    val backdropPath: String = ""

    @Json(name = "adult")
    val adult: Boolean = false

    @Json(name = "overview")
    val overview: String = ""

    @Json(name = "title")
    val title: String = ""

    @Json(name = "id")
    val id: Int = 0

    @field:Json(name = "release_date")
    val releaseDate: String? = null

}