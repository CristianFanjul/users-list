package com.cmf.userslist.domain.datasource

import com.cmf.userslist.Api
import com.cmf.userslist.model.User
import javax.inject.Inject

interface UsersDataSource {
    suspend fun requestUsers(): Result<List<User>>
    suspend fun editUser(user: User): Result<Unit>
    suspend fun deleteUser(userId: String): Result<Unit>
    suspend fun requestUser(userId: String): Result<User>
}

class UsersDataSourceImpl @Inject constructor(
    private val api: Api
) : UsersDataSource {

    override suspend fun requestUsers(): Result<List<User>> = try {
        Result.success(api.requestUsers())
    } catch (e: Exception) {
        Result.failure(e)
    }


    override suspend fun editUser(user: User) = try {
        Result.success(api.editUser(user))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteUser(userId: String): Result<Unit> = try {
        Result.success(api.deleteUser(userId))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun requestUser(userId: String): Result<User> = try {
        val user = api.requestUser(userId)
        user?.let { Result.success(user) }
            ?: Result.failure(RuntimeException("User does not exist."))
    } catch (e: Exception) {
        Result.failure(e)
    }
}