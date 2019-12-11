package com.example.movieapp.data.repository

import com.example.movieapp.data.crud.MovieCruder
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.service.MovieService
import com.example.movieapp.domain.Movie
import com.example.movieapp.domain.MovieDetails
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieRepositoryImpl : MovieRepository, KoinComponent {

    private val movieService: MovieService by inject()
    private val movieCruder: MovieCruder by inject()

    override fun getMovies(): Single<List<Movie>> = movieService.getMovies()

    override fun getNextPageMovies(page: Int): Single<List<Movie>> = movieService.getNextPageMovies(page)

    override fun saveMovie(movie: MovieDetails) = movieCruder.saveMovie(with(movie) {
        MovieEntity(
            id,
            title,
            popularity,
            voteAverage,
            posterPath,
            true,
            overview,
            voteCount,
            hasVideo,
            originalLanguage,
            originalTitle,
            backdropPath,
            adult,
            releaseDate

        )
    })

    override fun saveMovieDetails(movieDetails: MovieDetails): Completable = movieCruder.saveMovieDetails(
        with(movieDetails) {
            MovieDetailsEntity(
                id,
                title,
                overview,
                voteCount,
                releaseDate,
                voteAverage,
                hasVideo,
                popularity,
                posterPath,
                originalLanguage,
                originalTitle,
                backdropPath,
                adult,
                revenue,
                budget,
                runtime
            )
        })

    override fun saveMovieGenresJoin(movieId: Int, genreId: Int) = movieCruder.saveMovieGenresJoin(movieId, genreId)

    override fun saveGenres(genres: List<GenreEntity>): Completable = movieCruder.saveGenres(genres)

    override fun getFavourites(): Single<List<MovieEntity>> = movieCruder.getFavourites()

    override fun unfavouredMovie(movieId: Int) = movieCruder.unfavouredMovie(movieId)

    override fun getMoviesSearch(query: String, page: Int) = movieService.getMoviesSearch(query, page)
}