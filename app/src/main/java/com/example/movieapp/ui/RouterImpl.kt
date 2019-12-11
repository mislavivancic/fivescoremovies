package com.example.movieapp.ui

import com.example.movieapp.R
import com.example.movieapp.ui.details.MovieDetailsFragment
import com.example.movieapp.ui.favorite.FavouritesFragment
import com.example.movieapp.ui.movies.MainFragment

class RouterImpl : Router {
    companion object {
        private const val MAIN_FRAGMENT_ID = "MAIN_FRAGMENT"
        private const val FAVORITE_FRAGMENT_ID = "FAVORITE_FRAGMENT"
        private const val DETAILS_ID = "DETAILS_FRAGMENT"
    }

    private val mainFragment: BaseFragment = MainFragment()
    private val favoritesFragment: BaseFragment = FavouritesFragment()
    private lateinit var activity: BaseActivity

    override fun setActivity(activity: BaseActivity) {
        this.activity = activity
    }

    override fun showMovieList() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, mainFragment, MAIN_FRAGMENT_ID)
            .addToBackStack(null)
            .commit()
    }

    override fun navigateMovieList() {
        activity.supportFragmentManager.beginTransaction().replace(R.id.main_container, mainFragment).commit()
    }

    override fun navigateFavorites() {
        activity.supportFragmentManager.beginTransaction().replace(R.id.main_container, favoritesFragment).commit()
    }

    override fun showDetails(movieId: Int) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, MovieDetailsFragment.createFragment(movieId))
            .addToBackStack(null)
            .commit()

    }

    override fun goBack() {
        if (activity.supportFragmentManager.backStackEntryCount > 1) {
            activity.supportFragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }

}