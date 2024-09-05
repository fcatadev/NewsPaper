package com.example.newspaper.ui.auth.login

import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _loginEvent = MutableLiveData<Event<LoginViewEvent>>()
    val loginEvent: MutableLiveData<Event<LoginViewEvent>> get() = _loginEvent

    fun onSignUp() {
        _loginEvent.value = Event(LoginViewEvent.NavigateToRegister)
    }

}