package com.cmf.userslist.domain.usecase

import com.cmf.userslist.domain.repository.UsersRepository
import com.cmf.userslist.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(userId: String): Result<User> = usersRepository.requestUser(userId)
}