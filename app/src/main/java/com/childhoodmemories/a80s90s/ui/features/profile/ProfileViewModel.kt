package com.childhoodmemories.a80s90s.ui.features.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhoodmemories.a80s90s.domain.GetCurrentUserUseCase
import com.childhoodmemories.a80s90s.domain.GetLikedMemoriesUseCase
import com.childhoodmemories.a80s90s.domain.GetUserMemoriesUseCase
import com.childhoodmemories.a80s90s.domain.LogOutUseCase
import com.childhoodmemories.a80s90s.model.Memory
import com.childhoodmemories.a80s90s.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val logOutUseCase = LogOutUseCase()
    private val getCurrentUserUseCase = GetCurrentUserUseCase()
    private val getUserMemoriesUseCase = GetUserMemoriesUseCase()
    private val getLikedMemoriesUseCase = GetLikedMemoriesUseCase()

    // State
    private val _state by lazy { MutableStateFlow(State()) }
    private val state = _state.asStateFlow()

    @Composable
    fun getState(): State = state.collectAsState().value

    fun init() {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            val currentUser = getCurrentUserUseCase()
            val memories = getUserMemoriesUseCase()
            val likedMemories = getLikedMemoriesUseCase()
            currentUser?.let {
                _state.value = _state.value.copy(
                    user = currentUser,
                    name = it.firstname + " " + it.lastname,
                    avatar = it.avatar.orEmpty(),
                    screenState = ScreenState.Loaded,
                    memories = memories,
                    likeCounter = likedMemories.size
                )
            }
        }
    }

    fun logOut() {
        logOutUseCase()
        _state.value = _state.value.copy(sideEffect = SideEffect.NavigateToGetStarted)
    }

    @Immutable
    data class State(
        val sideEffect: SideEffect? = null,
        val screenState: ScreenState = ScreenState.Loading,
        val user: User? = null,
        val name: String = "",
        val avatar: String = "",
        val memories: List<Memory> = emptyList(),
        val likeCounter: Int = 0,
    )

    enum class SideEffect {
        NavigateToGetStarted,
    }

    enum class ScreenState {
        Loading, Loaded,
    }

}
