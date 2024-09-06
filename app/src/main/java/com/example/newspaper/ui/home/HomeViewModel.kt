package com.example.newspaper.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel() {

    private val _homeViewEvent = MutableLiveData<Event<HomeViewEvent>>()
    val homeViewEvent : MutableLiveData<Event<HomeViewEvent>> get() = _homeViewEvent

    fun signOut() {
        firebaseAuth.signOut()
        _homeViewEvent.value = Event(HomeViewEvent.NavigateToLogin)
    }

}