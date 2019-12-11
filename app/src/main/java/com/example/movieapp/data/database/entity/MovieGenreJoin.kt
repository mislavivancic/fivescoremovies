package com.example.movieapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "movie_genre_join",
    primaryKeys = ["movie_id", "genre_id"],
    foreignKeys = [ForeignKey(
        entity = MovieDetailsEntity::class,
        parentColumns = ["movie_id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["genre_id"],
            childColumns = ["genre_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class MovieGenreJoin(
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "genre_id") val genreId: Int
)