package com.example.movieapp.ui.favorite

import com.example.movieapp.ui.movies.view.MovieViewModel

interface FavouritesContract {

    interface FavouritesPresenter {
        fun getFavourites()
    }

    interface FavouritesView {
        fun showFavouriteMovies(movies: List<MovieViewModel>)
        fun onError(throwable: Throwable)
    }
}