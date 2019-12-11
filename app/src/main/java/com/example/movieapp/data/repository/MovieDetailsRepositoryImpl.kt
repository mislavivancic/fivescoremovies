package com.example.movieapp.data.repository

import com.example.movieapp.data.crud.MovieCruder
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.service.MovieDetailsService
import com.example.movieapp.ui.details.mapper.MovieDetailsMapper
import com.example.movieapp.ui.details.view.MovieDetailsViewModel
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailsRepositoryImpl : MovieDetailsRepository, KoinComponent {

    private val movieDetailsService: MovieDetailsService by inject()
    private val movieCruder: MovieCruder by inject()
    private val mapper: MovieDetailsMapper by inject()

    override fun getMovieDetails(movieId: Int): Single<MovieDetailsViewModel> =
        movieDetailsService.getMovieDetails(movieId).map { mapper.mapToDetailsView(it) }

    override fun getMovieDetailsDatabase(movieId: Int): Single<MovieDetailsViewModel> =
        Single.zip(movieCruder.getMovieDetailsDatabase(movieId), movieCruder.getMovieDetailsGenresDatabase(movieId),
            BiFunction { t1: MovieDetailsEntity, t2: List<GenreEntity> ->
                val movieDetails: MovieDetailsViewModel = mapper.mapEntityToDetailsView(t1)
                movieDetails.genres?.addAll(t2)
                movieDetails
            }
        )

}