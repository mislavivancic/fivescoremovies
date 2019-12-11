package com.example.movieapp.ui.details.mapper

import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.model.MovieDetails
import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import com.example.movieapp.ui.movies.view.MovieViewModel

interface MovieDetailsMapper {
    fun mapToDetailsView(movieDetails: MovieDetails): MovieDetailsViewModel
    fun mapToDetails(movieDetails: MovieDetailsViewModel): com.example.movieapp.domain.MovieDetails
    fun mapEntityToDetailsView(movieDetails: MovieDetailsEntity): MovieDetailsViewModel
    fun mapToMovieViewModel(movieDetails: MovieDetails): MovieViewModel
    fun mapViewToMovieViewModel(movieDetails: MovieDetailsViewModel): MovieViewModel
}