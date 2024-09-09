package com.example.newspaper.view.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("drawable")
fun ImageView.setDrawable(@DrawableRes resource: Int) {
    setImageResource(resource)
}