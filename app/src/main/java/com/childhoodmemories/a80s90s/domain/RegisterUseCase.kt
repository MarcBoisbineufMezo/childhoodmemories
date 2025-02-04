package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.UserRepository
import com.childhoodmemories.a80s90s.model.User
import java.util.UUID

class RegisterUseCase(
    private val userRepository: UserRepository = UserRepository
) {

    operator fun invoke(input: Input) {
        val user = User(
            id = UUID.randomUUID().toString(),
            email = input.email,
            password = input.password,
            firstname = input.firstName,
            lastname = input.lastName,
            avatar = input.avatar
        )
        userRepository.register(user = user)
    }

    data class Input(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val avatar: String = "https://lemagazinedesseries.com/wp-content/uploads/2019/01/magnum7.jpg"
    )
}