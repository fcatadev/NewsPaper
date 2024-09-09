package com.example.newspaper.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.example.newspaper.data.model.response.ArticleResponse
import com.example.newspaper.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: NewsRepository
) : BaseViewModel() {

    private val _homeViewEvent = MutableLiveData<Event<HomeViewEvent>>()
    val homeViewEvent: MutableLiveData<Event<HomeViewEvent>> get() = _homeViewEvent

    private val _articles = MutableLiveData<List<ArticleResponse>>()
    val articles: LiveData<List<ArticleResponse>> get() = _articles

    private val _allTechNews = MutableLiveData<List<ArticleResponse>>()
    val allTechNews: LiveData<List<ArticleResponse>> get() = _allTechNews

    val inputState = HomeInputState()

    fun getTopHeadlines(country: String, apiKey: String, context: Context, failText: String) {
        viewModelScope.launch {
            val response = repository.getTopHeadlines(country, apiKey)
            if (response.isSuccessful) {
                _articles.value = response.body()?.articles ?: emptyList()
            } else {
                Toast.makeText(context, failText, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getAllTechNews(query: String, apiKey: String, context: Context, failText: String) {
        viewModelScope.launch {
            val response = repository.getAllTechNews(query, apiKey)
            if (response.isSuccessful) {
                _allTechNews.value = response.body()?.articles ?: emptyList()
            } else {
                Toast.makeText(context, failText, Toast.LENGTH_LONG).show()
            }
        }
    }

}