package com.cmf.userslist.domain.repository

import com.cmf.userslist.domain.datasource.UsersDataSource
import com.cmf.userslist.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UsersRepository {
    suspend fun requestUsers(): Result<List<User>>
    suspend fun editUser(user: User): Result<Unit>
    suspend fun deleteUser(userId: String): Result<Unit>
    suspend fun requestUser(userId: String): Result<User>
}

class UsersRepositoryImpl @Inject constructor(
    private val dataSource: UsersDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {

    override suspend fun requestUsers(): Result<List<User>> = withContext(ioDispatcher) {
        dataSource.requestUsers()
    }

    override suspend fun editUser(user: User) = withContext(ioDispatcher) {
        dataSource.editUser(user)
    }

    override suspend fun deleteUser(userId: String): Result<Unit> = withContext(ioDispatcher) {
        dataSource.deleteUser(userId)
    }

    override suspend fun requestUser(userId: String): Result<User> = withContext(ioDispatcher) {
        dataSource.requestUser(userId)
    }
}