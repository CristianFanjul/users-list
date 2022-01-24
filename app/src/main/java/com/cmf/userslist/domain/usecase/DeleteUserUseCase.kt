package com.cmf.userslist.domain.usecase

import com.cmf.userslist.domain.repository.UsersRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend fun invoke(userId: String): Result<Unit> =
        repository.deleteUser(userId)

}