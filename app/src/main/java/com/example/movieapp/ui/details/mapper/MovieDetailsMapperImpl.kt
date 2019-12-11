package com.example.movieapp.ui.details.mapper

import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.model.MovieDetails
import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import com.example.movieapp.ui.movies.view.MovieViewModel

class MovieDetailsMapperImpl : MovieDetailsMapper {

    override fun mapToDetails(movieDetails: MovieDetailsViewModel) =
        with(movieDetails) {
            com.example.movieapp.domain.MovieDetails(
                voteCount,
                hasVideo,
                voteAverage,
                popularity,
                posterPath,
                originalLanguage,
                originalTitle,
                genres,
                backdropPath,
                adult,
                revenue,
                budget,
                runtime,
                overview,
                "",
                title,
                id,
                releaseDate
            )
        }

    override fun mapToDetailsView(movieDetails: MovieDetails): MovieDetailsViewModel = with(movieDetails) {
        MovieDetailsViewModel(
            id,
            voteCount,
            voteAverage,
            genres?.map { GenreEntity(it.id, it.name) }?.toMutableList(),
            backdropPath,
            revenue,
            budget,
            runtime,
            overview,
            title,
            releaseDate,
            popularity,
            posterPath,
            hasVideo,
            originalLanguage,
            originalTitle,
            adult
        )
    }

    override fun mapEntityToDetailsView(movieDetails: MovieDetailsEntity): MovieDetailsViewModel = with(movieDetails) {
        MovieDetailsViewModel(
            id,
            voteCount,
            voteAverage,
            mutableListOf(),
            backdropPath,
            revenue,
            budget,
            runtime,
            overview,
            title,
            releaseDate,
            popularity,
            posterPath,
            hasVideo,
            originalLanguage,
            originalTitle,
            adult
        )
    }

    override fun mapToMovieViewModel(movieDetails: MovieDetails): MovieViewModel =
        with(movieDetails) {
            MovieViewModel(
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
                null,
                backdropPath,
                adult,
                releaseDate
            )
        }

    override fun mapViewToMovieViewModel(movieDetails: MovieDetailsViewModel): MovieViewModel =
        with(movieDetails) {
            MovieViewModel(
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
                null,
                backdropPath,
                adult,
                releaseDate
            )
        }

}