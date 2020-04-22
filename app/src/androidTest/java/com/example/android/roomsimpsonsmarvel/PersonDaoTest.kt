package com.example.android.roomsimpsonsmarvel

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.roomsimpsonsmarvel.db.PersonDao
import com.example.android.roomsimpsonsmarvel.db.SimpsonsMarvelDatabase
import com.example.android.roomsimpsonsmarvel.model.Person
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PersonDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: SimpsonsMarvelDatabase
    private lateinit var personDao: PersonDao
    private lateinit var person1: Person
    private lateinit var person2: Person
    private lateinit var person3: Person
    private lateinit var person4: Person
    private lateinit var person5: Person
    private lateinit var person6: Person
    private lateinit var person7: Person
    private lateinit var person8: Person
    private lateinit var person9: Person
    private lateinit var person10: Person
    private lateinit var person11: Person
    private lateinit var person12: Person
    private lateinit var person13: Person
    private lateinit var person14: Person
    private lateinit var person15: Person
    private lateinit var person16: Person

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
        personDao = database.personDao()

        person1 = Person(null, "Homer Simpson")
        person2 = Person(null, "Marge Simpson")
        person3 = Person(null, "Lisa Simpson")
        person4 = Person(null, "Maggie Simpson")
        person5 = Person(null, "Patty Bouvier")
        person6 = Person(null, "Selma Bouvier")
        person7 = Person(null, "Kent Brockman")
        person8 = Person(null, "Ned Flanders")
        person9 = Person(null, "Barney Gumble")
        person10 = Person(null, "Itchy")
        person11 = Person(null, "Eric Cartman")
        person12 = Person(null, "Scratchy")
        person13 = Person(null, "Crusty the Clown")
        person14 = Person(null, "Montgomery Burns")
        person15 = Person(null, "Mayor Joe Quimby")
        person16 = Person(null, "Groundskeeper Willie")

        personDao.insertPerson(person1)
        personDao.insertPerson(person2)
        personDao.insertPerson(person3)
        personDao.insertPerson(person4)
        personDao.insertPerson(person5)
        personDao.insertPerson(person6)
        personDao.insertPerson(person7)
        personDao.insertPerson(person8)
        personDao.insertPerson(person9)
        personDao.insertPerson(person10)
        personDao.insertPerson(person11)
        personDao.insertPerson(person12)
        personDao.insertPerson(person13)
        personDao.insertPerson(person14)
        personDao.insertPerson(person15)
        personDao.insertPerson(person16)
    }

    @Test
    fun testFindAll() {
        val numberOfPeople = personDao.findAll().size

        Assert.assertEquals(16, numberOfPeople)
    }

    @Test
    fun testFindById() {
        val foundPerson = personDao.findById(1)

        Assert.assertEquals("Homer Simpson", foundPerson.name)
    }

    @Test
    fun testInsertPerson() {

        val previousNumberOfPeople = personDao.findAll().size

        val person = Person(null, "Bart Simpson")
        personDao.insertPerson(person)

        val numberOfPeople = personDao.findAll().size

        val numberOfNewPeople = numberOfPeople - previousNumberOfPeople

        Assert.assertEquals(1, numberOfNewPeople)

    }

    @Test
    fun testUpdatePerson() {
        val krusty = personDao.findById(13)
        krusty.name = "Krusty the Clown"
        personDao.updatePerson(krusty)
        val newKrusty = personDao.findById(13)
        Assert.assertEquals("Krusty the Clown", newKrusty.name)
    }

    @Test
    fun testDeletePerson() {

        val previousNumberOfPeople = personDao.findAll().size

        val personToDelete = personDao.findById(11)
        personDao.deletePerson(personToDelete)

        val numberOfPeople = personDao.findAll().size

        val numberOfPeopleRemoved = previousNumberOfPeople - numberOfPeople

        Assert.assertEquals(1, numberOfPeopleRemoved)
    }

    @After
    fun tearDown() {
        database.close()
    }
}