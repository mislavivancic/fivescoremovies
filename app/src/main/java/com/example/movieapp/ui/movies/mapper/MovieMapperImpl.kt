package com.example.movieapp.ui.movies.mapper

import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.model.ApiMovie
import com.example.movieapp.domain.Movie
import com.example.movieapp.ui.movies.view.MovieViewModel

class MovieMapperImpl : MovieMapper {

    override fun mapApiToMovieViewModel(apiMovies: List<Movie>): List<MovieViewModel> =
        apiMovies.map {
            with(it) {
                MovieViewModel(
                    id,
                    title,
                    popularity,
                    voteAverage,
                    posterPath,
                    isFavourite,
                    overview,
                    voteCount,
                    hasVideo,
                    originalLanguage,
                    originalTitle,
                    genreIds,
                    backdropPath,
                    adult,
                    releaseDate
                )
            }
        }

    override fun mapEntityToMovieViewModel(movieEntity: List<MovieEntity>): List<MovieViewModel> =
        movieEntity.map {
            with(it) {
                MovieViewModel(
                    id,
                    title,
                    popularity,
                    voteAverage / 2,
                    posterPath,
                    isFavourite,
                    overview,
                    voteCount,
                    hasVideo,
                    originalLanguage,
                    originalTitle,
                    null,
                    backdropPath,
                    adult,
                    releaseDate
                )
            }
        }

    override fun mapEntityToMovie(movieView: List<MovieEntity>) = movieView.map {
        with(it) {
            Movie(
                title,
                overview,
                voteAverage / 2,
                id,
                voteCount,
                hasVideo,
                popularity,
                posterPath,
                originalLanguage,
                originalTitle,
                null,
                backdropPath,
                adult,
                releaseDate,
                isFavourite
            )
        }
    }

    override fun mapApiToMovie(apiMovies: List<ApiMovie>) = apiMovies.map {
        with(it) {
            Movie(
                title,
                overview,
                voteAverage / 2,
                id,
                voteCount,
                hasVideo,
                popularity,
                posterPath,
                originalLanguage,
                originalTitle,
                genreIds,
                backdropPath,
                adult,
                releaseDate
            )
        }
    }
}