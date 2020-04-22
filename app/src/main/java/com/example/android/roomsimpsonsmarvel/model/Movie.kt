package com.example.android.roomsimpsonsmarvel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var title: String,
    var year: Int,
    @ColumnInfo(name = "show_time") var showTime: String
)