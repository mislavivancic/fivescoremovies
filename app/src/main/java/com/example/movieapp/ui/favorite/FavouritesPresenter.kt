package com.example.movieapp.ui.favorite

import com.example.movieapp.data.presenter.BasePresenter
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.movies.mapper.MovieMapper
import com.example.movieapp.ui.movies.view.MovieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavouritesPresenter(private val view: FavouritesContract.FavouritesView?) : BasePresenter(), KoinComponent,
    FavouritesContract.FavouritesPresenter {

    private val movieRepository: MovieRepository by inject()
    private val mapper: MovieMapper by inject()

    override fun getFavourites() {
        addSubscription(
            movieRepository.getFavourites()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map { mapper.mapEntityToMovieViewModel(it).sortedBy { it.popularity }.reversed() }
                .subscribe(this::showFavourites, this::onError)
        )
    }

    private fun showFavourites(movies: List<MovieViewModel>) {
        view?.showFavouriteMovies(movies)
    }

    private fun onError(throwable: Throwable) {
        view?.onError(throwable)
    }

    override fun activate() {
        getFavourites()
    }
}