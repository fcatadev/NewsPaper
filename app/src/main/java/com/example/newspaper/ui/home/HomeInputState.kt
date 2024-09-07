package com.example.newspaper.ui.home

import androidx.databinding.ObservableField
import com.example.newspaper.extensions.EMPTY

data class HomeInputState (

    var data: ObservableField<String> = ObservableField(String.EMPTY)

)