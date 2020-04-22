package com.example.android.roomsimpsonsmarvel.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.roomsimpsonsmarvel.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun findAll(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun findById(id: Long): Movie

    @Insert(onConflict = IGNORE)
    fun insertMovie(movie: Movie): Long

    @Update(onConflict = REPLACE)
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

}