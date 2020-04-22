package com.example.android.roomsimpsonsmarvel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val name: String
)