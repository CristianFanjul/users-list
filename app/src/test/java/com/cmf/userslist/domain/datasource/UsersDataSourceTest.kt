package com.cmf.userslist.domain.datasource

import com.cmf.userslist.Api
import com.cmf.userslist.model.User
import com.cmf.userslist.utils.TestUsersListHelper
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UsersDataSourceTest {

    private val api: Api = mockk()
    private val dataSource: UsersDataSourceImpl = UsersDataSourceImpl(api)

    @Before
    fun setUp() {
        coEvery { api.requestUsers() } returns TestUsersListHelper().createListOfUsers()
    }

    @Test
    fun `test datasource returns data`() {
        val result = runBlocking { dataSource.requestUsers() }

        Assert.assertNotNull(result.getOrNull())
    }

    @Test
    fun `edit user replaces data`() {
        val user = User("1", "b", "c", "d", "f")
        val expectedUsers = TestUsersListHelper().createListOfUsers().toMutableList().apply {
            set(0, user)
        }
        coEvery { api.editUser(user = user) } coAnswers {
            coEvery { api.requestUsers() } returns expectedUsers
        }

        runBlocking {
            dataSource.editUser(user)
            val users = dataSource.requestUsers()
            val user1 = users.getOrNull()?.get(0)!!

            assertEquals("1", user1.id)
            assertEquals("b", user1.name)
            assertEquals("c", user1.lastName)
            assertEquals("d", user1.biography)
        }
    }

    @Test
    fun `test user is deleted`() {
        val expectedUsers = TestUsersListHelper().createListOfUsers().toMutableList().apply {
            removeAll { it.id == "2" }
        }
        coEvery { api.deleteUser("2") } coAnswers {
            coEvery { api.requestUsers() } returns expectedUsers
        }

        runBlocking {
            dataSource.deleteUser("2")
            val users = dataSource.requestUsers()

            assertEquals(2, users.getOrNull()!!.size)
        }
    }
}