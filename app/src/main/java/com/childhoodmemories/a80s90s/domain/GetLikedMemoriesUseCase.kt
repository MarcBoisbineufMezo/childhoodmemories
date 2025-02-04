package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.MemoryRepository
import com.childhoodmemories.a80s90s.model.Memory

class GetLikedMemoriesUseCase(
    private val memoriesRepository: MemoryRepository = MemoryRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase = GetCurrentUserUseCase()
) {
    suspend operator fun invoke(): List<Memory> {
        val currentUser = getCurrentUserUseCase()
        currentUser ?: return emptyList()
        return memoriesRepository.loadLikedMemories(currentUser.id)
    }
}