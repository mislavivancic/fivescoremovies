package com.example.movieapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.movieapp.data.crud.MovieCruder
import com.example.movieapp.data.crud.MovieCruderImpl
import com.example.movieapp.data.database.MovieDatabase
import com.example.movieapp.data.repository.MovieDetailsRepository
import com.example.movieapp.data.repository.MovieDetailsRepositoryImpl
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.service.MovieDetailsService
import com.example.movieapp.data.service.MovieDetailsServiceImpl
import com.example.movieapp.data.service.MovieService
import com.example.movieapp.data.service.MovieServiceImpl
import com.example.movieapp.ui.*
import com.example.movieapp.ui.details.MovieDetailsContract
import com.example.movieapp.ui.details.MovieDetailsFragment
import com.example.movieapp.ui.details.MovieDetailsPresenter
import com.example.movieapp.ui.details.mapper.MovieDetailsMapper
import com.example.movieapp.ui.details.mapper.MovieDetailsMapperImpl
import com.example.movieapp.ui.favorite.FavouritesContract
import com.example.movieapp.ui.favorite.FavouritesFragment
import com.example.movieapp.ui.favorite.FavouritesPresenter
import com.example.movieapp.ui.movies.MainFragment
import com.example.movieapp.ui.movies.MoviePresenter
import com.example.movieapp.ui.movies.MoviesContract
import com.example.movieapp.ui.movies.mapper.MovieMapper
import com.example.movieapp.ui.movies.mapper.MovieMapperImpl
import com.example.movieapp.ui.util.AnimationLoader
import com.example.movieapp.ui.util.ImageLoader
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.text.NumberFormat

class MovieApplication : Application() {

    private val moviesModule = module {

        single<MovieCruder> { MovieCruderImpl() }
        single<MovieService> { MovieServiceImpl() }
        single<MovieDetailsService> { MovieDetailsServiceImpl() }

        single<MovieRepository> { MovieRepositoryImpl() }
        single<MovieDetailsRepository> { MovieDetailsRepositoryImpl() }

        scope(named<MainFragment>()) {
            scoped { (view: MoviesContract.MovieView) -> MoviePresenter(view) }
        }
        scope(named<FavouritesFragment>()) {
            scoped { (view: FavouritesContract.FavouritesView) -> FavouritesPresenter(view) }
        }

        scope(named<MovieDetailsFragment>()) {
            scoped { (view: MovieDetailsContract.DetailsView) -> MovieDetailsPresenter(view) }
        }

        scope(named<MainActivity>()) {
            scoped { (activity: BaseActivity) -> MainPresenter(activity) }
            scoped<Router> { RouterImpl() }
        }

        single { ImageLoader(Glide.with(this@MovieApplication)) }
        factory { AnimationLoader(this@MovieApplication) }
        factory { NumberFormat.getCurrencyInstance() }
        factory { LayoutInflater.from(this@MovieApplication) }

        single<MovieMapper> { MovieMapperImpl() }
        single<MovieDetailsMapper> { MovieDetailsMapperImpl() }

        single { getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

        single { createDatabase() }

    }

    private fun createDatabase() = Room.databaseBuilder(this@MovieApplication, MovieDatabase::class.java, "movie-database").build()

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(moviesModule)

        startKoin {
            modules(moduleList)
        }
    }
}