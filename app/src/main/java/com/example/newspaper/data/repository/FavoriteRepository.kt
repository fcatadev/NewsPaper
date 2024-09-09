package com.example.newspaper.data.repository

import com.example.newspaper.data.model.favorite.FavoriteArticle
import com.example.newspaper.local.dao.FavoriteArticleDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavoriteArticleDao
) {

    // Flow kullanarak favori makalelerin anlık olarak takip edilmesini sağlıyoruz.
    val allFavorites: Flow<List<FavoriteArticle>> = dao.getAllFavoriteArticles()

    suspend fun addFavorite(article: FavoriteArticle) {
        dao.insertArticle(article)
    }

    suspend fun deleteFavorite(article: FavoriteArticle) {
        dao.deleteArticle(article)
    }

    suspend fun isFavorite(article: FavoriteArticle): Boolean {
        return dao.getFavoriteByUrl(article.url) != null
    }
}


