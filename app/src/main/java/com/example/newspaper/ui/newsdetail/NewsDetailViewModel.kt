package com.example.newspaper.ui.newsdetail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.core.Event
import com.example.newspaper.data.model.favorite.FavoriteArticle
import com.example.newspaper.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : BaseViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private var currentArticle: FavoriteArticle? = null

    val favoriteArticles: LiveData<List<FavoriteArticle>> = repository.allFavorites.asLiveData()

    fun checkIfFavorite(article: FavoriteArticle) {
        currentArticle = article
        viewModelScope.launch {
            _isFavorite.postValue(repository.isFavorite(article))
        }
    }

    fun toggleFavorite(article: FavoriteArticle, context: Context, text: String) {
        viewModelScope.launch {
            if (_isFavorite.value == true) {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            } else {
                repository.addFavorite(article)
                _isFavorite.postValue(true)
            }
        }
    }
}