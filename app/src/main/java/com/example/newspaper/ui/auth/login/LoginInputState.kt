package com.example.newspaper.ui.auth.login

import androidx.databinding.ObservableField
import com.example.newspaper.extensions.EMPTY

data class LoginInputState (
    var email: ObservableField<String> = ObservableField(String.EMPTY),
    var password: ObservableField<String> = ObservableField(String.EMPTY),
    var isInvalidEmail: ObservableField<Boolean> = ObservableField(false),
    var isInvalidPassword: ObservableField<Boolean> = ObservableField(false),
    var isProgressVisible: ObservableField<Boolean> = ObservableField(false),
)