package com.cmf.userslist.ui

import com.cmf.userslist.model.User
import com.cmf.userslist.ui.main.UserUiModel

class UserMapper {
    fun userToUiModel(user: User): UserUiModel {
        return UserUiModel(
            id = user.id,
            name = "${user.name} ${user.lastName}",
            biography = user.biography,
            imageUri = user.imageUri
        )
    }
}