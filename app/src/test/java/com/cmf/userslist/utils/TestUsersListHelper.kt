package com.cmf.userslist.utils

import com.cmf.userslist.model.User

class TestUsersListHelper {
    fun createListOfUsers(): List<User> {
        return listOf(
            User("1", "First", "One", "Something 1", "http://one"),
            User("2", "Second", "Two", "Something 2", "http://two"),
            User("3", "Third", "Three", "Something 3", "http://three")
        )
    }

    fun createUser(): User =
        User(
            "1",
            "First",
            "One",
            "Something 1",
            "http://one"
        )
}