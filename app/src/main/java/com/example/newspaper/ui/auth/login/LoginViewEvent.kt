package com.example.newspaper.ui.auth.login

sealed class LoginViewEvent {

    object NavigateToRegister : LoginViewEvent()

    object NavigateToHome : LoginViewEvent()

    object IncorrectLogin : LoginViewEvent()

}