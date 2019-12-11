package com.example.movieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.database.entity.GenreEntity
import io.reactivex.Single

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre_table")
    fun getAll(): Single<List<GenreEntity>>

    @Query("SELECT * FROM genre_table WHERE genre_id=:genreId")
    fun getById(genreId: Int): Single<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genreEntity: List<GenreEntity>)
}