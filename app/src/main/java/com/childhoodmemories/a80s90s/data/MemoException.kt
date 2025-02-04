package com.childhoodmemories.a80s90s.data


sealed class MemoException : Exception() {
    sealed class SignIn : MemoException() {
        data class InvalidEmail(override val message: String = "") : SignIn()
        data class PasswordNotCorrect(override val message: String = "") : SignIn()
    }

    sealed class Register : MemoException() {
        data class EmailAlreadyUsed(override val message: String = "") : Register()
        data class BadCredentials(override val message: String = "") : Register()
    }

}