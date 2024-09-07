package com.example.newspaper.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.example.newspaper.data.remote.preferences.PreferencesConstants
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    private val _homeViewEvent = MutableLiveData<Event<HomeViewEvent>>()
    val homeViewEvent : MutableLiveData<Event<HomeViewEvent>> get() = _homeViewEvent

    private val sharedPrefs = context.getSharedPreferences(PreferencesConstants.USER_PREFS, Context.MODE_PRIVATE)

    fun signOut() {
        firebaseAuth.signOut()
        sharedPrefs.edit().putBoolean(PreferencesConstants.IS_LOGGED_IN, false).apply()
        _homeViewEvent.value = Event(HomeViewEvent.NavigateToLogin)
    }

}