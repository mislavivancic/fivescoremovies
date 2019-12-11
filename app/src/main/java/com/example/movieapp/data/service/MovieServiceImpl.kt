package com.example.movieapp.data.service

import com.example.movieapp.data.api.MovieApiFactory
import com.example.movieapp.domain.Movie
import com.example.movieapp.ui.movies.mapper.MovieMapper
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieServiceImpl : MovieService, KoinComponent {

    private val apiMapper: MovieMapper by inject()

    override fun getMovies(): Single<List<Movie>> =
        MovieApiFactory.getApi().getMovies(MovieApiFactory.key).map { apiMapper.mapApiToMovie(it.movieList) }

    override fun getNextPageMovies(page: Int): Single<List<Movie>> =
        MovieApiFactory.getApi().getNextPage(MovieApiFactory.key, page).map { apiMapper.mapApiToMovie(it.movieList) }

    override fun getMoviesSearch(query: String, page: Int): Single<List<Movie>> =
        MovieApiFactory.getApi().getMoviesSearchQuery(MovieApiFactory.key, query, page).map { apiMapper.mapApiToMovie(it.movieList) }
}