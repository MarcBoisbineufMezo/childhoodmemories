package com.childhoodmemories.a80s90s.ui.features.getStarted

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetStartedViewModel : ViewModel() {

    // State
    private val _state by lazy { MutableStateFlow(State()) }
    private val state = _state.asStateFlow()

    @Composable
    fun getState(): State = state.collectAsState().value

    fun init() {
    }

    fun onSignInClick() {
        _state.value = _state.value.copy(screenState = ScreenState.SignIn)
    }

    fun onSignUpClick() {
        _state.value = _state.value.copy(screenState = ScreenState.SignUp)
    }

    fun signIn() {
        TODO("Not yet implemented")
    }

    fun reset() {
        _state.value = _state.value.copy(
            screenState = ScreenState.ChooseSignInOrSignUp,
            email = "",
            password = ""
        )
    }


    @Immutable
    data class State(
        val screenState: ScreenState = ScreenState.ChooseSignInOrSignUp,
        val email: String = "",
        val password: String = "",
    )

    enum class ScreenState {
        ChooseSignInOrSignUp,
        SignIn,
        SignUp,
    }
}