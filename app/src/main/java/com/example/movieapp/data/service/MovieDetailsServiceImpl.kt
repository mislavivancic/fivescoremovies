package com.example.movieapp.data.service

import com.example.movieapp.data.api.MovieApiFactory
import com.example.movieapp.data.model.MovieDetails
import io.reactivex.Single

class MovieDetailsServiceImpl : MovieDetailsService {

    override fun getMovieDetails(movieId: Int): Single<MovieDetails> = MovieApiFactory.getApi().getMovie(movieId, MovieApiFactory.key)

}