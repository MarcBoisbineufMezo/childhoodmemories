package com.childhoodmemories.a80s90s.ui.features.getStarted

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.childhoodmemories.a80s90s.data.MemoException
import com.childhoodmemories.a80s90s.domain.GetCurrentUserUseCase
import com.childhoodmemories.a80s90s.domain.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetStartedViewModel : ViewModel() {

    private val signInUseCase = SignInUseCase()
    private val getCurrentUserUseCase = GetCurrentUserUseCase()

    // State
    private val _state by lazy { MutableStateFlow(State()) }
    private val state = _state.asStateFlow()

    @Composable
    fun getState(): State = state.collectAsState().value

    fun init() {
        val currentUser = getCurrentUserUseCase()
        if (currentUser != null) {
            _state.value = _state.value.copy(sideEffect = SideEffect.NavigateToHome)
        } else {
            _state.value = _state.value.copy(screenState = ScreenState.ChooseSignInOrSignUp)
        }
    }

    fun onSignInClick() {
        _state.value = _state.value.copy(screenState = ScreenState.SignIn)
    }

    fun onSignUpClick() {
        _state.value = _state.value.copy(screenState = ScreenState.SignUp)
    }

    fun signIn() {
        try {
            signInUseCase(SignInUseCase.Input(_state.value.email, _state.value.password))
            _state.value = _state.value.copy(sideEffect = SideEffect.NavigateToHome)
        } catch (e: MemoException.SignIn.InvalidEmail) {
            _state.value = _state.value.copy(screenState = ScreenState.EmailError)
        } catch (e: MemoException.SignIn.PasswordNotCorrect) {
            _state.value = _state.value.copy(screenState = ScreenState.PasswordError)
        }
    }

    fun reset() {
        _state.value = _state.value.copy(
            screenState = ScreenState.ChooseSignInOrSignUp,
            email = "",
            password = ""
        )
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }


    @Immutable
    data class State(
        val sideEffect: SideEffect? = null,
        val screenState: ScreenState = ScreenState.Loading,
        val email: String = "emma.thompson@example.com",
        val password: String = "securePass123",
    )

    enum class SideEffect {
        NavigateToHome,
    }

    enum class ScreenState {
        Loading,
        ChooseSignInOrSignUp,
        SignIn,
        SignUp,
        EmailError,
        PasswordError,
    }
}