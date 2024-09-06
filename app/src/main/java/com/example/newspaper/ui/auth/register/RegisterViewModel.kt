package com.example.newspaper.ui.auth.register

import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {

    private val _registerEvent = MutableLiveData<Event<RegisterViewEvent>>()
    val registerEvent: MutableLiveData<Event<RegisterViewEvent>> get() = _registerEvent

    fun onSignIn() {
        _registerEvent.value = Event(RegisterViewEvent.NavigateToLogin)
    }

}