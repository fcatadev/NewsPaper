package com.example.newspaper.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>> = _viewEvent

    protected fun sendEvent(event: Any) {
        _viewEvent.value = Event(event)
    }
}