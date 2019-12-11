package com.example.movieapp.data.repository

import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.domain.Movie
import com.example.movieapp.domain.MovieDetails
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(): Single<List<Movie>>

    fun getNextPageMovies(page: Int): Single<List<Movie>>

    fun saveMovie(movie: MovieDetails): Completable

    fun unfavouredMovie(movieId: Int): Completable

    fun saveMovieDetails(movieDetails: MovieDetails): Completable

    fun getFavourites(): Single<List<MovieEntity>>

    fun saveMovieGenresJoin(movieId: Int, genreId: Int): Completable

    fun saveGenres(genres: List<GenreEntity>): Completable

    fun getMoviesSearch(query: String, page: Int): Single<List<Movie>>
}