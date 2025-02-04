package com.childhoodmemories.a80s90s.domain

import com.childhoodmemories.a80s90s.data.MemoryRepository
import com.childhoodmemories.a80s90s.model.Memory

class AddMemoryUseCase(
    private val memoryRepository: MemoryRepository = MemoryRepository,
) {
    suspend operator fun invoke(memory: Memory) {
        memoryRepository.addMemory(memory)
    }
}