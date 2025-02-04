package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.UserRepository
import com.childhoodmemories.a80s90s.model.User

class GetCurrentUserUseCase(
    private val userRepository: UserRepository = UserRepository
) {
    operator fun invoke(): User? {
        return userRepository.currentUser
    }
}