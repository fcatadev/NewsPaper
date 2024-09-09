package com.example.newspaper.ui.auth.login

sealed class LoginViewEvent {

    object NavigateToRegister : LoginViewEvent()

    object NavigateToHome : LoginViewEvent()

    object IncorrectLogin : LoginViewEvent()

    data class ShowMessage(val message: String) : LoginViewEvent()

    data class ShowError(val error: String) : LoginViewEvent()

    object IsInputEmpty : LoginViewEvent()

}