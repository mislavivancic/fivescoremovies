package com.example.movieapp.ui.details

import android.net.ConnectivityManager
import com.example.movieapp.data.presenter.BasePresenter
import com.example.movieapp.data.repository.MovieDetailsRepository
import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailsPresenter(private val view: MovieDetailsContract.DetailsView?) : MovieDetailsContract.DetailsPresenter, BasePresenter(),
    KoinComponent {

    private val movieDetailsRepository: MovieDetailsRepository by inject()
    private var movieId: Int = 0

    override fun init(movieId: Int) {
        this.movieId = movieId
    }

    override fun activate() {
        if (isNetworkAvailable()) {
            getMovie()
        } else {
            getMovieDatabase()
        }
    }

    override fun getMovie() {

        addSubscription(
            movieDetailsRepository.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showMovieDetails, this::onError)
        )
    }

    override fun getMovieDatabase() {
        addSubscription(
            movieDetailsRepository.getMovieDetailsDatabase(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showMovieDetails, this::onError)
        )
    }

    private fun showMovieDetails(movieDetails: MovieDetailsViewModel) {
        view?.showMovieDetails(movieDetails)
    }

    private fun onError(throwable: Throwable) {
        view?.onErrorDetails(throwable)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager by inject()
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}