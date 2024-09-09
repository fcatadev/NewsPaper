package com.example.newspaper.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.data.model.favorite.FavoriteArticle
import com.example.newspaper.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : BaseViewModel() {

    val favoriteArticles: LiveData<List<FavoriteArticle>> = repository.allFavorites.asLiveData()

    fun removeFavorite(article: FavoriteArticle) {
        viewModelScope.launch {
            repository.deleteFavorite(article)
        }
    }
}

