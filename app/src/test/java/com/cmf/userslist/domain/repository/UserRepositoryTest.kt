package com.cmf.userslist.domain.repository

import com.cmf.userslist.domain.datasource.UsersDataSource
import com.cmf.userslist.utils.TestUsersListHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private val dataSource: UsersDataSource = mockk()
    lateinit var repository: UsersRepository

    @Before
    fun setUp() {
        repository = UsersRepositoryImpl(dataSource)
    }

    @Test
    fun `test repository returns list of users`() {
        val expectedResult = TestUsersListHelper().createListOfUsers()
        coEvery { dataSource.requestUsers() } returns Result.success(expectedResult)

        val result = runBlocking { repository.requestUsers() }

        assertTrue(result.isSuccess)
        assertEquals(3, result.getOrNull()?.size)
        coVerify(exactly = 1) { dataSource.requestUsers() }
    }

    @Test
    fun `test repository returns exception`() {
        coEvery { dataSource.requestUsers() } returns Result.failure(RuntimeException("DS Error"))

        val result = runBlocking { repository.requestUsers() }

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals("DS Error", result.exceptionOrNull()?.message)
        coVerify(exactly = 1) { dataSource.requestUsers() }
    }

    @Test
    fun `test edit user in invoked`() {
        val user = TestUsersListHelper().createUser()
        coEvery { dataSource.editUser(any()) } returns Result.success(Unit)

        runBlocking { repository.editUser(user) }

        coVerify(exactly = 1) { dataSource.editUser(user) }
    }

    @Test
    fun `test delete user in invoked`() {
        coEvery { dataSource.deleteUser(any()) } returns Result.success(Unit)

        runBlocking { repository.deleteUser("id") }

        coVerify(exactly = 1) { dataSource.deleteUser("id") }
    }
}