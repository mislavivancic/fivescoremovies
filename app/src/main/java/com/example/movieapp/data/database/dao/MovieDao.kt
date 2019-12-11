package com.example.movieapp.data.database.dao

import androidx.room.*
import com.example.movieapp.data.database.entity.MovieEntity
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    fun getAll(): Single<List<MovieEntity>>

    @Insert
    fun insertAll(movieEntities: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)

    @Query("DELETE FROM movieentity WHERE id = :movieId")
    fun deleteById(movieId: Int)
}