package com.childhoodmemories.a80s90s.ui.features.addMemory

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhoodmemories.a80s90s.domain.AddMemoryUseCase
import com.childhoodmemories.a80s90s.domain.GetCurrentUserUseCase
import com.childhoodmemories.a80s90s.domain.GetMemoriesUseCase
import com.childhoodmemories.a80s90s.model.Category
import com.childhoodmemories.a80s90s.model.Memory
import com.childhoodmemories.a80s90s.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddMemoryViewModel : ViewModel() {

    private val addMemoryUseCase = AddMemoryUseCase()
    private val getCurrentUserUseCase = GetCurrentUserUseCase()
    private val getMemoriesUseCase = GetMemoriesUseCase()

    // State
    private val _state by lazy { MutableStateFlow(State()) }
    private val state = _state.asStateFlow()

    @Composable
    fun getState(): State = state.collectAsState().value

    fun init() {
        viewModelScope.launch {
            val memories = getMemoriesUseCase()
            _state.value = _state.value.copy(imageId = (memories.size + 1).toString())
        }
    }

    fun saveImage(uri: Uri) {
        _state.value = _state.value.copy(image = uri.toString())
    }

    fun onTitleChanged(title: String) {
        _state.value = _state.value.copy(title = title)

    }

    fun onDescriptionChanged(description: String) {
        _state.value = _state.value.copy(description = description)
    }

    fun onValidateMemory() {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            val memories = getMemoriesUseCase()
            user?.let {
                val memory = Memory(
                    id = memories.size + 1,
                    title = _state.value.title,
                    description = _state.value.description,
                    image = _state.value.image.toString(),
                    user = user,
                    category = Category.MUSIC,
                )
                addMemoryUseCase(memory)
                _state.value = _state.value.copy(sideEffect = SideEffect.NavigateToHome)
            }
        }
    }

    @Immutable
    data class State(
        val sideEffect: SideEffect? = null,
        val screenState: ScreenState = ScreenState.Loading,
        val user: User? = null,
        val memory: Memory? = null,
        val title: String = "",
        val description: String = "",
        val image: String? = null,
        val imageId: String? = null,
    )

    enum class SideEffect {
        NavigateToHome,
    }

    enum class ScreenState {
        Loading
    }

}
