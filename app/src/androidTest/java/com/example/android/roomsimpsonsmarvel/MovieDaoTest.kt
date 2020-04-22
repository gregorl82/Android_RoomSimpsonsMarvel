package com.example.android.roomsimpsonsmarvel

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.roomsimpsonsmarvel.db.MovieDao
import com.example.android.roomsimpsonsmarvel.db.SimpsonsMarvelDatabase
import com.example.android.roomsimpsonsmarvel.model.Movie
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: SimpsonsMarvelDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var movie1: Movie
    private lateinit var movie2: Movie
    private lateinit var movie3: Movie
    private lateinit var movie4: Movie
    private lateinit var movie5: Movie

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        try {
            database = Room.inMemoryDatabaseBuilder(context, SimpsonsMarvelDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        } catch (e: Exception) {
            Log.i(this.javaClass.simpleName, e.message)
        }
        movieDao = database.movieDao()

        movie1 = Movie(null, "Iron Man", 2008, "17:00")
        movie2 = Movie(null, "The Incredible Hulk", 2008, "23:55")
        movie3 = Movie(null, "Iron Man 2", 2010, "18:45")
        movie4 = Movie(null, "Thor", 2011, "15:45")
        movie5 = Movie(null, "Captain America: The First Avenger", 2011, "14:15")

        movieDao.insertMovie(movie1)
        movieDao.insertMovie(movie2)
        movieDao.insertMovie(movie3)
        movieDao.insertMovie(movie4)
        movieDao.insertMovie(movie5)
    }

    @Test
    fun testFindMovieById() {
        val firstMovie = movieDao.findById(1)
        val firstMovieTitle = firstMovie.title
        val firstMovieShowTime = firstMovie.showTime
        Assert.assertEquals(movie1.title, firstMovieTitle)
        Assert.assertEquals(movie1.showTime, firstMovieShowTime)
    }

    @Test
    fun testInsertMovie() {
        val previousNumberOfMovies = movieDao.findAll().size

        val newMovie = Movie(null, "Avengers Assemble", 2012, "14.45")
        movieDao.insertMovie(newMovie)

        val numberOfMovies = movieDao.findAll().size

        val numberOfNewMovies = numberOfMovies - previousNumberOfMovies

        Assert.assertEquals(1, numberOfNewMovies)
    }

    @Test
    fun testUpdateMovie() {
        val movieToUpdate = movieDao.findById(3)
        movieToUpdate.showTime = "19:45"
        movieDao.updateMovie(movieToUpdate)

        val updatedMovie = movieDao.findById(3)
        Assert.assertEquals("19:45", updatedMovie.showTime)
    }

    @Test
    fun testDeleteMovie() {
        val previousNumberOfMovies = movieDao.findAll().size

        val movieToDelete = movieDao.findById(2)
        movieDao.deleteMovie(movieToDelete)

        val numberOfMovies = movieDao.findAll().size

        val numberOfMoviesDeleted = previousNumberOfMovies - numberOfMovies

        Assert.assertEquals(1, numberOfMoviesDeleted)
    }

    @After
    fun tearDown() {
        database.close()
    }
}