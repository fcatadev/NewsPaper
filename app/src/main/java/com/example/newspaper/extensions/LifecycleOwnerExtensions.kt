package com.example.newspaper.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.newspaper.core.Event

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, observer: (T) -> Unit) {
    liveData.observe(this) {
        it?.getContentIfNotHandled()?.let { t -> observer(t) }
    }
}