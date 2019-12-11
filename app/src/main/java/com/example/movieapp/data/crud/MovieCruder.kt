package com.example.movieapp.data.crud

import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.database.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MovieCruder {

    fun saveMovie(movie: MovieEntity): Completable

    fun saveMovieDetails(movieDetails: MovieDetailsEntity): Completable

    fun unfavouredMovie(movieId: Int): Completable

    fun getFavourites(): Single<List<MovieEntity>>

    fun saveMovieGenresJoin(movieId: Int, genreId: Int): Completable

    fun saveGenres(genres: List<GenreEntity>): Completable

    fun getMovieDetailsDatabase(movieId: Int): Single<MovieDetailsEntity>

    fun getMovieDetailsGenresDatabase(movieId: Int): Single<List<GenreEntity>>
}