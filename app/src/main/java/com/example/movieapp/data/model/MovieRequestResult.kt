package com.example.movieapp.data.model

import com.squareup.moshi.Json

class MovieRequestResult {

    @Json(name = "page")
    val page: Int? = null

    @field:Json(name = "total_results")
    val totalResults: Int? = null

    @field:Json(name = "total_pages")
    val totalPages: Int? = null

    @field:Json(name = "results")
    val movieList: List<ApiMovie> = listOf()

}