package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.MemoException
import com.childhoodmemories.a80s90s.data.UserRepository

class SignInUseCase(
    private val userRepository: UserRepository = UserRepository
) {

    @Throws(MemoException.SignIn::class)
    operator fun invoke(input: Input) {
        userRepository.siginIn(input.email, input.password)
    }

    data class Input(
        val email: String,
        val password: String
    )
}