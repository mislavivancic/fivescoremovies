package com.example.movieapp.ui.movies

import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.presenter.BasePresenter
import com.example.movieapp.data.repository.MovieDetailsRepository
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.domain.Movie
import com.example.movieapp.ui.details.mapper.MovieDetailsMapper
import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import com.example.movieapp.ui.movies.mapper.MovieMapper
import com.example.movieapp.ui.movies.mapper.MovieMapperImpl
import com.example.movieapp.ui.movies.view.MovieViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviePresenter(private val view: MoviesContract.MovieView?) : MoviesContract.MoviePresenter, BasePresenter(), KoinComponent {

    companion object {
        private const val START_PAGE = 1
        private var currentPage = 1
    }

    private val movieRepository: MovieRepository by inject()
    private val movieViewMapper: MovieMapper by inject()
    private val detailsViewMapper: MovieDetailsMapper by inject()
    private val movieDetailsRepository: MovieDetailsRepository by inject()

    override fun activate() {
        getMovies("")
    }

    override fun getMovies(query: String) {
        if (query.isBlank()) {
            addSubscription(
                movieRepository.getMovies()
                    .zipWith((
                            movieRepository.getFavourites()
                                .flatMap {
                                    Single.just(MovieMapperImpl().mapEntityToMovie(it))
                                }),
                        BiFunction { t1: List<Movie>, t2: List<Movie> ->
                            (t1.filter { t2.contains(it).not() } + t2).sortedBy { it.popularity }.reversed()
                        })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showMovies, this::onError)
            )

        } else {

            addSubscription(
                movieRepository.getMoviesSearch(query, START_PAGE)
                    .zipWith((
                            movieRepository.getFavourites()
                                .flatMap {
                                    Single.just(MovieMapperImpl().mapEntityToMovie(it))
                                }),
                        BiFunction { t1: List<Movie>, t2: List<Movie> ->
                            (t1.filter { t2.contains(it).not() } + t2.filter { t1.contains(it) }).sortedBy { it.popularity }.reversed()
                        })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showMovies, this::onError)
            )

        }
    }

    override fun getNextPageMovies() {
        currentPage++

        addSubscription(
            movieRepository.getNextPageMovies(currentPage)
                .zipWith((
                        movieRepository.getFavourites()
                            .flatMap {
                                Single.just(MovieMapperImpl().mapEntityToMovie(it))
                            }),
                    BiFunction { t1: List<Movie>, t2: List<Movie> ->
                        (t1.filter { t2.contains(it).not() } + t2.filter { t1.contains(it) }).sortedBy { it.popularity }.reversed()
                    })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showNextMovies, this::onError)
        )

    }

    private fun showMovies(movies: List<Movie>) {
        view?.showMovies(movieViewMapper.mapApiToMovieViewModel(movies))
    }

    private fun showNextMovies(movies: List<Movie>) {
        view?.showNextPage(movieViewMapper.mapApiToMovieViewModel(movies))
    }

    override fun saveMovie(movieViewModel: MovieViewModel) {
        addSubscription(
            movieDetailsRepository.getMovieDetails(movieViewModel.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveAll, this::onError)
        )
    }

    private fun saveAll(movieDetails: MovieDetailsViewModel) {
        addSubscription(
            movieRepository.saveMovie(detailsViewMapper.mapToDetails(movieDetails)).subscribeOn(Schedulers.io()).subscribe()
        )
        addSubscription(
            movieRepository.saveMovieDetails(detailsViewMapper.mapToDetails(movieDetails)).subscribeOn(Schedulers.io()).subscribe(
                this::onSuccessDatabase,
                this::onError
            )
        )

        if (movieDetails.genres != null) {
            addSubscription(movieRepository.saveGenres(movieDetails.genres.map {
                GenreEntity(
                    it.id,
                    it.name
                )
            }).subscribeOn(Schedulers.io()).subscribe(this::onSuccessDatabase, this::onError))
            for (genre in movieDetails.genres) {
                addSubscription(
                    movieRepository.saveMovieGenresJoin(movieDetails.id, genre.id).subscribeOn(Schedulers.io()).subscribe(
                        this::onSuccessDatabase,
                        this::onError
                    )
                )
            }
        }

    }

    override fun unfavouredMovie(movieId: Int) {
        addSubscription(
            movieRepository.unfavouredMovie(movieId).subscribeOn(Schedulers.io()).subscribe()
        )
    }

    private fun onError(throwable: Throwable) {
        view?.onErrorMovies(throwable)
    }

    private fun onSuccessDatabase() {
    }

}