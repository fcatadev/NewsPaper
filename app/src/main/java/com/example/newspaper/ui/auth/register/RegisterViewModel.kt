package com.example.newspaper.ui.auth.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.example.newspaper.extensions.isEmailValid
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel() {

    private val _registerEvent = MutableLiveData<Event<RegisterViewEvent>>()
    val registerEvent: MutableLiveData<Event<RegisterViewEvent>> get() = _registerEvent

    val inputState = RegisterInputState()

    val errorMessage = MutableLiveData<String>()

    fun onRegister() {
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

        firebaseAuth.createUserWithEmailAndPassword(emailValue, passwordValue)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    inputState.isInvalidEmail.set(false)
                    inputState.isInvalidPassword.set(false)
                    inputState.isProgressVisible.set(false)
                    _registerEvent.value = Event(RegisterViewEvent.NavigateToLogin)
                } else {
                    errorMessage.value = task.exception?.message ?: "Kayıt işlemi başarısız."
                    Log.d("135Hata: ", "${errorMessage.value}")
                }
            }
    }

    fun onSignIn() {
        _registerEvent.value = Event(RegisterViewEvent.NavigateToLogin)
    }

}