package com.childhoodmemories.a80s90s.ui.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhoodmemories.a80s90s.domain.GetCurrentUserUseCase
import com.childhoodmemories.a80s90s.domain.GetLikedMemoriesUseCase
import com.childhoodmemories.a80s90s.domain.LikeMemoryUseCase
import com.childhoodmemories.a80s90s.domain.GetMemoriesUseCase
import com.childhoodmemories.a80s90s.model.Memory
import com.childhoodmemories.a80s90s.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val getMemoriesUseCase = GetMemoriesUseCase()
    private val getCurrentUserUseCase = GetCurrentUserUseCase()
    private val likeMemoryUseCase = LikeMemoryUseCase()
    private val getLikedMemoriesUseCase = GetLikedMemoriesUseCase()

    // State
    private val _state by lazy { MutableStateFlow(State()) }
    private val state = _state.asStateFlow()

    @Composable
    fun getState(): State = state.collectAsState().value

    fun init() {
        loadCurrentUser()
        viewModelScope.launch {
            val memories = getMemoriesUseCase().shuffled()
            val likedMemories = getLikedMemoriesUseCase()
            _state.value = _state.value.copy(
                screenState = ScreenState.Loaded,
                memories = memories,
                likedMemories = likedMemories,
            )
        }
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            val currentUser = getCurrentUserUseCase()
            _state.value = _state.value.copy(
                user = currentUser
            )
        }
    }

    fun onLikeClicked(memory: Memory) {
        val likedMemories = if (isLiked(memory)) {
            _state.value.likedMemories - memory
        } else {
            _state.value.likedMemories + memory
        }
        _state.value = _state.value.copy(likedMemories = likedMemories)
        viewModelScope.launch {
            likeMemoryUseCase(memory)
        }
    }

    fun isLiked(memory: Memory): Boolean {
        return _state.value.likedMemories.contains(memory)
    }

    @Immutable
    data class State(
        val screenState: ScreenState = ScreenState.Loading,
        val user: User? = null,
        val memories: List<Memory> = emptyList(),
        val likedMemories: List<Memory> = emptyList()
    )

    enum class ScreenState {
        Loading, Loaded,
    }

}
