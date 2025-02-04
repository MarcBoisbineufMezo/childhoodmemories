package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.UserRepository

class LogOutUseCase(
    private val userRepository: UserRepository = UserRepository
) {
    // TODO improve with suspend
    operator fun invoke() {
        userRepository.currentUser = null
    }
}