package com.example.movieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.database.entity.MovieDetailsEntity
import io.reactivex.Single

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM MOVIE_DETAILS_TABLE")
    fun getAll(): Single<List<MovieDetailsEntity>>

    @Query("SELECT * FROM MOVIE_DETAILS_TABLE WHERE movie_id=:movieId")
    fun getById(movieId: Int): Single<MovieDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDetailsEntity: MovieDetailsEntity)
}