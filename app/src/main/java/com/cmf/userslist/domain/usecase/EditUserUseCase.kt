package com.cmf.userslist.domain.usecase

import com.cmf.userslist.domain.repository.UsersRepository
import com.cmf.userslist.model.User
import javax.inject.Inject

class EditUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(user: User) = usersRepository.editUser(user)
}