package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.MemoryRepository
import com.childhoodmemories.a80s90s.data.UserRepository
import com.childhoodmemories.a80s90s.model.Memory

class GetUserMemoriesUseCase(
    private val userRepository: UserRepository = UserRepository,
    private val memoryRepository: MemoryRepository = MemoryRepository,
) {
    suspend operator fun invoke(): List<Memory> {
        val currentUser = userRepository.currentUser
        return memoryRepository.loadMemories().filter { it.user == currentUser }
    }
}