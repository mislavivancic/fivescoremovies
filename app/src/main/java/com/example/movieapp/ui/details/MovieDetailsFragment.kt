package com.example.movieapp.ui.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.movieapp.R
import com.example.movieapp.data.api.ImageApi
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.ui.BaseFragment
import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import com.example.movieapp.ui.util.ImageLoader
import kotlinx.android.synthetic.main.movie_details_layout.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import java.text.NumberFormat

class MovieDetailsFragment : BaseFragment(), MovieDetailsContract.DetailsView {

    companion object {
        private const val delimiter = "|"
        private const val movieTitleFormat = "%s (%s)"
        private const val voteFormat = "%3.2f/10 (%d votes)"
        private const val runtimeFormat = "$delimiter %d min $delimiter"
        private const val MOVIE_ID = "MOVIE_ID"
        private const val SCOPE_ID = "DETAILS_SCOPE_ID"

        @JvmStatic
        fun createFragment(movieId: Int) = MovieDetailsFragment().apply {
            this.arguments = Bundle().apply {
                putInt(MOVIE_ID, movieId)
            }
        }
    }

    private val scope = getKoin().getOrCreateScope(SCOPE_ID, named<MovieDetailsFragment>())
    private val movieDetailsPresenter: MovieDetailsPresenter by scope.inject { parametersOf(this) }
    private val imageLoader: ImageLoader by inject()
    private val currencyFormat: NumberFormat by inject()
    private var movieId: Int? = 0

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        movieId = arguments?.getInt(MOVIE_ID)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movie_details_layout, container, false)
        setPresenter(movieDetailsPresenter)

        movieDetailsPresenter.init(movieId!!)

        return view
    }

    override fun onErrorDetails(t: Throwable) {
        Log.e("ERROR", "Reason: " + t.localizedMessage)
        Toast.makeText(context, "Unable to fetch movieDetails: " + t.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun showMovieDetails(movie: MovieDetailsViewModel) {
        showDetails(movie)
    }

    private fun showDetails(movieDetails: MovieDetailsViewModel) {

        genresTextView.text = getMovieGenres(movieDetails.genres)
        imageLoader.loadImage(ImageApi.imageBaseURL + movieDetails.backdropPath, imageView)
        movieTitle.text = String.format(movieTitleFormat, movieDetails.title, movieDetails.releaseDate?.split("-")?.get(0) ?: "")

        if (movieDetails.runtime == null) {
            runtimeTextView.visibility = View.GONE
        } else {
            runtimeTextView.text = String.format(runtimeFormat, movieDetails.runtime)
        }
        movieOverview.text = movieDetails.overview
        ratingBar.rating = movieDetails.voteAverage.toFloat() / 2
        voteCountTextView.text = String.format(voteFormat, movieDetails.voteAverage, movieDetails.voteCount)
        revenueTextView.text = currencyFormat.format(movieDetails.revenue)
        budgetTextView.text = currencyFormat.format(movieDetails.budget)
    }

    private fun getMovieGenres(genres: MutableList<GenreEntity>?): String {
        val genresString = StringBuilder()
        genresString.append("$delimiter ")

        if (genres != null) {
            for (genre in genres) {
                genresString.append(genre.name + " $delimiter ")
            }
        }
        return genresString.toString()
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}