package com.cmf.userslist.domain.datasource

import com.cmf.userslist.model.User

class UsersGenerator {
    fun createUsersList(): List<User> {
        return listOf(
            User(
                "hash-code-a",
                "Milla",
                "Gully",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                "https://gravatar.com/avatar/d645ebb88cfb469df1a21b89cc4a1ee4?s=400&d=robohash&r=x"
            ),
            User(
                "hash-code-b",
                "Khan",
                "Louis",
                "When an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "https://gravatar.com/avatar/a469e4405d9f21f08477a1e22279bcb8?s=400&d=robohash&r=x"
            ),
            User(
                "hash-code-c",
                "Aias",
                "Wyndham",
                "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
                "https://gravatar.com/avatar/bfc4374f4cd41c9a089c2214aed85132?s=400&d=robohash&r=x"
            ),
            User(
                "hash-code-d",
                "Adzo",
                "Causer",
                "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages.",
                "https://gravatar.com/avatar/a68a9b565a2f39f13fadc04711dbe439?s=400&d=robohash&r=x"
            ),
            User(
                "hash-code-e",
                "Carmelo",
                "Garber",
                "And more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://gravatar.com/avatar/a8ff86d988395e14ec2a7b586d47ddb7?s=400&d=robohash&r=x"
            ),
            User(
                "hash-code-f",
                "Lisa",
                "Banister",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://gravatar.com/avatar/58df25dfe29b8ed879c9454cc0486628?s=400&d=robohash&r=x"
            ),
        )
    }
}