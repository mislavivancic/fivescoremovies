package com.example.movieapp.ui.movies.mapper

import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.model.ApiMovie
import com.example.movieapp.domain.Movie
import com.example.movieapp.ui.movies.view.MovieViewModel

interface MovieMapper {

    fun mapApiToMovieViewModel(apiMovies: List<Movie>): List<MovieViewModel>

    fun mapEntityToMovieViewModel(movieEntity: List<MovieEntity>): List<MovieViewModel>

    fun mapEntityToMovie(movieView: List<MovieEntity>): List<Movie>

    fun mapApiToMovie(apiMovies: List<ApiMovie>): List<Movie>
}