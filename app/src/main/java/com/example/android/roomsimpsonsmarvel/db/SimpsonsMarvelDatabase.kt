package com.example.android.roomsimpsonsmarvel.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.roomsimpsonsmarvel.model.Movie
import com.example.android.roomsimpsonsmarvel.model.Person

@Database(entities = arrayOf(Person::class, Movie::class), version = 2)
abstract class SimpsonsMarvelDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun movieDao(): MovieDao

    companion object {

        private var instance: SimpsonsMarvelDatabase? = null

        fun getInstance(context: Context): SimpsonsMarvelDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    SimpsonsMarvelDatabase::class.java,
                    "SimpsonsMarvel"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as SimpsonsMarvelDatabase
        }
    }
}