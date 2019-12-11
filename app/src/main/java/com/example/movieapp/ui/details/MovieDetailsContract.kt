package com.example.movieapp.ui.details

import com.example.movieapp.ui.details.view.MovieDetailsViewModel

interface MovieDetailsContract {

    interface DetailsPresenter {
        fun getMovie()
        fun getMovieDatabase()
        fun init(movieId: Int)
    }

    interface DetailsView {
        fun showMovieDetails(movie: MovieDetailsViewModel)
        fun onErrorDetails(t: Throwable)

    }

}