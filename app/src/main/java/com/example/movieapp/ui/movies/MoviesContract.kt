package com.example.movieapp.ui.movies

import com.example.movieapp.ui.movies.view.MovieViewModel

interface MoviesContract {

    interface MoviePresenter {
        fun saveMovie(movieViewModel: MovieViewModel)
        fun unfavouredMovie(movieId: Int)
        fun getMovies(query: String)
        fun getNextPageMovies()

    }

    interface MovieView {
        fun showMovies(movies: List<MovieViewModel>)
        fun showNextPage(movies: List<MovieViewModel>)
        fun onErrorMovies(t: Throwable)
    }
}