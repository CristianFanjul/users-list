package com.cmf.userslist

import com.cmf.userslist.domain.datasource.UsersGenerator
import com.cmf.userslist.model.User
import javax.inject.Inject

interface Api {
    suspend fun requestUsers(): List<User>
    suspend fun editUser(user: User)
    suspend fun deleteUser(userId: String)
    suspend fun requestUser(userId: String): User?
}

class FakeApi @Inject constructor() : Api {
    private val users = UsersGenerator().createUsersList().toMutableList()

    override suspend fun requestUsers() = users

    override suspend fun editUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        users[index] = user
    }

    override suspend fun deleteUser(userId: String) {
        val result = users.removeAll { it.id == userId }
        if (!result) throw RuntimeException("Can't remove item.")
    }

    override suspend fun requestUser(userId: String): User? {
        return users.firstOrNull { it.id == userId }
    }

}