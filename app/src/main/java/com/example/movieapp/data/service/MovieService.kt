package com.example.movieapp.data.service

import com.example.movieapp.domain.Movie
import io.reactivex.Single

interface MovieService {
    fun getMovies(): Single<List<Movie>>
    fun getNextPageMovies(page: Int): Single<List<Movie>>
    fun getMoviesSearch(query: String, page: Int): Single<List<Movie>>
}