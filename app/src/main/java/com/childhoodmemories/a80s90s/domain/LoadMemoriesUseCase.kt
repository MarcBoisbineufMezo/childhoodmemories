package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.MemoryRepository
import com.childhoodmemories.a80s90s.model.Memory

class LoadMemoriesUseCase(
    private val memoriesRepository: MemoryRepository = MemoryRepository
) {
    suspend operator fun invoke(): List<Memory> {
        return memoriesRepository.loadMemories()
    }

}