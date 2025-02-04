package com.childhoodmemories.a80s90s.ui.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhoodmemories.a80s90s.domain.GetCurrentUserUseCase
import com.childhoodmemories.a80s90s.domain.LoadMemoriesUseCase
import com.childhoodmemories.a80s90s.model.Memory
import com.childhoodmemories.a80s90s.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val loadMemoriesUseCase = LoadMemoriesUseCase()
    val getCurrentUserUseCase = GetCurrentUserUseCase()

    // State
    private val _state by lazy { MutableStateFlow(State()) }
    private val state = _state.asStateFlow()

    @Composable
    fun getState(): State = state.collectAsState().value

    fun init() {
        loadCurrentUser()
        viewModelScope.launch {
            val memories = loadMemoriesUseCase()
            _state.value = _state.value.copy(
                screenState = ScreenState.Loaded,
                memories = memories
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

    @Immutable
    data class State(
        val sideEffect: SideEffect? = null,
        val screenState: ScreenState = ScreenState.Loading,
        val user: User? = null,
        val memories: List<Memory> = emptyList()
    )

    enum class SideEffect {
    }

    enum class ScreenState {
        Loading,
        Loaded,
    }

}
