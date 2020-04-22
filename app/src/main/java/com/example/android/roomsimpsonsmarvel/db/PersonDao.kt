package com.example.android.roomsimpsonsmarvel.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.roomsimpsonsmarvel.model.Person

@Dao
interface PersonDao {

    @Query("SELECT * FROM people")
    fun findAll(): List<Person>

    @Query("SELECT * FROM people WHERE id = :id")
    fun findById(id: Long): Person

    @Insert(onConflict = IGNORE)
    fun insertPerson(person: Person): Long

    @Update(onConflict = REPLACE)
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)
}