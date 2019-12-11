package com.example.movieapp.data.model

import com.squareup.moshi.Json
import java.io.Serializable

class Genre :Serializable{

    @Json(name = "id")
    val id: Int = 0

    @Json(name = "name")
    val name: String = ""

}
