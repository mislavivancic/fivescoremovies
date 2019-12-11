package com.example.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.database.dao.GenreDao
import com.example.movieapp.data.database.dao.MovieDao
import com.example.movieapp.data.database.dao.MovieDetailsDao
import com.example.movieapp.data.database.dao.MovieGenreJoinDao
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.database.entity.MovieGenreJoin

@Database(entities = [MovieEntity::class, MovieDetailsEntity::class, GenreEntity::class, MovieGenreJoin::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun genreDao(): GenreDao
    abstract fun movieGenreJoinDao(): MovieGenreJoinDao
}