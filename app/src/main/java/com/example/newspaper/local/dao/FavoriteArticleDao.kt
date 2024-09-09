package com.example.newspaper.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newspaper.data.model.favorite.FavoriteArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: FavoriteArticle)

    @Delete
    suspend fun deleteArticle(article: FavoriteArticle)

    @Query("SELECT * FROM favorite_article")
    fun getAllFavoriteArticles(): Flow<List<FavoriteArticle>>

    @Query("SELECT * FROM favorite_article WHERE url = :url LIMIT 1")
    suspend fun getFavoriteByUrl(url: String): FavoriteArticle?
}



