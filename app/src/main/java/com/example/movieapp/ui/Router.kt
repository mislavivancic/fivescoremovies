package com.example.movieapp.ui

interface Router {
    fun setActivity(activity: BaseActivity)

    fun showMovieList()

    fun navigateMovieList()

    fun navigateFavorites()

    fun showDetails(movieId: Int)

    fun goBack()
}