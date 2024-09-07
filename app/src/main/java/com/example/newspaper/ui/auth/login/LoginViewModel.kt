package com.example.newspaper.ui.auth.login

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
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    private val _loginEvent = MutableLiveData<Event<LoginViewEvent>>()
    val loginEvent: MutableLiveData<Event<LoginViewEvent>> get() = _loginEvent

    val inputState = LoginInputState()

    private val sharedPrefs = context.getSharedPreferences(PreferencesConstants.USER_PREFS, Context.MODE_PRIVATE)

    fun onLogin() {
        val emailValue = inputState.email.get().orEmpty()
        val passwordValue = inputState.password.get().orEmpty()
        inputState.isProgressVisible.set(true)

        firebaseAuth.signInWithEmailAndPassword(emailValue, passwordValue)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    inputState.isProgressVisible.set(false)
                    if (inputState.isRememberMeChecked.get() == true) {
                        sharedPrefs.edit().putBoolean(PreferencesConstants.IS_LOGGED_IN, true).apply()
                    }
                    _loginEvent.value = Event(LoginViewEvent.NavigateToHome)
                } else {
                    _loginEvent.value = Event(LoginViewEvent.IncorrectLogin)
                    inputState.isProgressVisible.set(false)
                }
            }
    }

    fun onSignUp() {
        _loginEvent.value = Event(LoginViewEvent.NavigateToRegister)
    }

}