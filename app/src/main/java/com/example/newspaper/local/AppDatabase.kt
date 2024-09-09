package com.example.newspaper.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newspaper.data.model.favorite.FavoriteArticle
import com.example.newspaper.local.dao.FavoriteArticleDao

@Database(entities = [FavoriteArticle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteArticleDao() : FavoriteArticleDao

}