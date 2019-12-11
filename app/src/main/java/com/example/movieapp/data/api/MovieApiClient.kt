package com.example.movieapp.data.api

import com.example.movieapp.data.model.MovieDetails
import com.example.movieapp.data.model.MovieRequestResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") key: String): Single<MovieRequestResult>

    @GET("discover/movie")
    fun getNextPage(@Query("api_key") key: String, @Query("page") page: Int): Single<MovieRequestResult>

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movieId: Int, @Query("api_key") key: String): Single<MovieDetails>

    @GET("search/movie")
    fun getMoviesSearchQuery(@Query("api_key") key: String, @Query("query") query: String, @Query("page") page: Int): Single<MovieRequestResult>

}