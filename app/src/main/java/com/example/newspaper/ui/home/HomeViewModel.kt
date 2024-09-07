package com.example.newspaper.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.example.newspaper.data.remote.preferences.PreferencesConstants
import com.example.newspaper.data.repository.NewsRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context,
    private val repository: NewsRepository
) : BaseViewModel() {

    private val _homeViewEvent = MutableLiveData<Event<HomeViewEvent>>()
    val homeViewEvent : MutableLiveData<Event<HomeViewEvent>> get() = _homeViewEvent

    private val _authors = MutableLiveData<String>()
    val authors: LiveData<String> get() = _authors

    val inputState = HomeInputState()
    private val sharedPrefs = context.getSharedPreferences(PreferencesConstants.USER_PREFS, Context.MODE_PRIVATE)

    fun signOut() {
        firebaseAuth.signOut()
        sharedPrefs.edit().putBoolean(PreferencesConstants.IS_LOGGED_IN, false).apply()
        _homeViewEvent.value = Event(HomeViewEvent.NavigateToLogin)
    }

    fun getTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            val response = repository.getTopHeadlines(country, apiKey)
            val authorList = response.body()?.articles?.mapNotNull { it.author } ?: listOf("Author bilgisi bulunamadı")
            _authors.value = authorList.toString()
            Log.d("198List: ", "$authorList")
        }
    }

}