package com.example.newspaper.data.repository

import com.example.newspaper.data.api.ApiService
import com.example.newspaper.data.model.response.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTopHeadlines(country: String, apiKey: String): Response<NewsResponse> {
        return apiService.getTopHeadlines(country, apiKey)
    }

    suspend fun getAllTechNews(query: String, apiKey: String): Response<NewsResponse> {
        return apiService.getAllTechNews(query, apiKey)
    }
}