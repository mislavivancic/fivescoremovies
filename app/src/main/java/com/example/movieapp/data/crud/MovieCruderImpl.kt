package com.example.movieapp.data.crud

import com.example.movieapp.data.database.MovieDatabase
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.database.entity.MovieGenreJoin
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieCruderImpl : MovieCruder, KoinComponent {

    private val database: MovieDatabase by inject()

    override fun saveMovie(movie: MovieEntity) = Completable.fromAction {
        database.movieDao().insert(movie)
    }

    override fun saveMovieDetails(movieDetails: MovieDetailsEntity): Completable =
        Completable.fromAction {
            database.movieDetailsDao().insert(movieDetails)
        }

    override fun saveMovieGenresJoin(movieId: Int, genreId: Int): Completable =
        Completable.fromAction {
            database.movieGenreJoinDao().insert(MovieGenreJoin(movieId, genreId))
        }

    override fun saveGenres(genres: List<GenreEntity>): Completable =
        Completable.fromAction {
            database.genreDao().insert(genres)
        }

    override fun unfavouredMovie(movieId: Int) = Completable.fromAction { database.movieDao().deleteById(movieId) }

    override fun getFavourites(): Single<List<MovieEntity>> = database.movieDao().getAll()

    override fun getMovieDetailsDatabase(movieId: Int): Single<MovieDetailsEntity> = database.movieDetailsDao().getById(movieId)

    override fun getMovieDetailsGenresDatabase(movieId: Int): Single<List<GenreEntity>> = database.movieGenreJoinDao().getMovieGenres(movieId)
}