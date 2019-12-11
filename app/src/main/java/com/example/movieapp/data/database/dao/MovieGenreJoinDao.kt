package com.example.movieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieGenreJoin
import io.reactivex.Single

@Dao
interface MovieGenreJoinDao {

    @Query("SELECT * FROM genre_table inner JOIN movie_genre_join ON genre_table.genre_id = movie_genre_join.genre_id WHERE movie_genre_join.movie_id=:movieId")
    fun getMovieGenres(movieId: Int): Single<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieGenre: MovieGenreJoin)
}