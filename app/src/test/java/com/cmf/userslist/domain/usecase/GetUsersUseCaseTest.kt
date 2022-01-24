package com.cmf.userslist.domain.usecase

import com.cmf.userslist.domain.repository.UsersRepository
import com.cmf.userslist.utils.TestUsersListHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val repository: UsersRepository = mockk()

    @Test
    fun `test a list of users is returned successfully`() {
        val expectedUsersList = TestUsersListHelper().createListOfUsers()
        coEvery { repository.requestUsers() } returns Result.success(expectedUsersList)

        val result = runBlocking { GetUsersUseCase(repository).invoke() }

        coVerify(exactly = 1) { repository.requestUsers() }
        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(result.getOrNull()?.size, 3)
    }

    @Test
    fun `test use case returns exception`() {
        coEvery { repository.requestUsers() } returns Result.failure(RuntimeException("Something wrong"))

        val result = runBlocking { GetUsersUseCase(repository).invoke() }

        coVerify(exactly = 1) { repository.requestUsers() }
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals("Something wrong", result.exceptionOrNull()?.message)
    }
}