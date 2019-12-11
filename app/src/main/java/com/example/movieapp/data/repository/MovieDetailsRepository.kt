package com.example.movieapp.data.repository

import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import io.reactivex.Single

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Int): Single<MovieDetailsViewModel>
    fun getMovieDetailsDatabase(movieId: Int): Single<MovieDetailsViewModel>
}