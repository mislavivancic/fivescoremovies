package com.example.movieapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_table")
data class GenreEntity(

    @PrimaryKey @ColumnInfo(name = "genre_id") val id: Int,
    @ColumnInfo(name = "name") val name: String
)