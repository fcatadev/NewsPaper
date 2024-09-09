package com.example.newspaper.ui.home

sealed class HomeViewEvent {

    object NavigateToLogin : HomeViewEvent()

    data class NavigateToNewsDetail(
        val author: String?,
        val title: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String?,
        val content: String
    ) : HomeViewEvent()

}