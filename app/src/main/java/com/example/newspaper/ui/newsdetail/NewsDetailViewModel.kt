package com.example.newspaper.ui.newsdetail

import androidx.lifecycle.MutableLiveData
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(

) : BaseViewModel() {

    private val _newsDetailViewEvent = MutableLiveData<Event<NewsDetailViewEvent>>()
    val newsDetailViewEvent: MutableLiveData<Event<NewsDetailViewEvent>> get() = _newsDetailViewEvent

    val inputState = NewsDetailInputState()

}