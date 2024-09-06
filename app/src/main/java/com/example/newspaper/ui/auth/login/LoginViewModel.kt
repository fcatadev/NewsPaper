package com.example.newspaper.ui.auth.login

import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.example.newspaper.extensions.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel() {

    private val _loginEvent = MutableLiveData<Event<LoginViewEvent>>()
    val loginEvent: MutableLiveData<Event<LoginViewEvent>> get() = _loginEvent

    val inputState = LoginInputState()

    val errorMessage = MutableLiveData<String>()

    fun onLogin() {
        val emailValue = inputState.email.get().orEmpty()
        val passwordValue = inputState.password.get().orEmpty()
        inputState.isProgressVisible.set(true)
        inputState.isInvalidEmail.set(false)
        inputState.isInvalidPassword.set(false)

        if (!emailValue.isEmailValid()) {
            inputState.isInvalidEmail.set(true)
            inputState.isProgressVisible.set(false)
            return
        }

        if (passwordValue.length < 8) {
            inputState.isInvalidPassword.set(true)
            inputState.isProgressVisible.set(false)
            return
        }

        firebaseAuth.signInWithEmailAndPassword(emailValue, passwordValue)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    inputState.isInvalidEmail.set(false)
                    inputState.isInvalidPassword.set(false)
                    inputState.isProgressVisible.set(false)
                    _loginEvent.value = Event(LoginViewEvent.NavigateToHome)
                } else {
                    errorMessage.value = task.exception?.message ?: "Giriş işlemi başarısız."
                }
            }
    }

    fun onSignUp() {
        _loginEvent.value = Event(LoginViewEvent.NavigateToRegister)
    }

}