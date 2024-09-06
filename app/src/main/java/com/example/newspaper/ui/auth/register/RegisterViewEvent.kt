package com.example.newspaper.ui.auth.register

sealed class RegisterViewEvent {

    object NavigateToLogin : RegisterViewEvent()

}