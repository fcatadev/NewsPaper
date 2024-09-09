package com.example.newspaper.ui.newsdetail

import androidx.databinding.ObservableField
import com.example.newspaper.extensions.ZERO

data class NewsDetailInputState (
    var favoriteButtonDrawable: ObservableField<Int> = ObservableField(Int.ZERO)
)