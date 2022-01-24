package com.cmf.userslist.domain.usecase

import com.cmf.userslist.domain.repository.UsersRepository
import com.cmf.userslist.model.User
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend fun invoke(user: User): Result<Unit> =
        repository.addUser(user)
}