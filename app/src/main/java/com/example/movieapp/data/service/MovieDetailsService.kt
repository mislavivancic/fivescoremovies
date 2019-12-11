package com.example.movieapp.data.service

import com.example.movieapp.data.model.MovieDetails
import io.reactivex.Single

interface MovieDetailsService {
    fun getMovieDetails(movieId: Int): Single<MovieDetails>
}