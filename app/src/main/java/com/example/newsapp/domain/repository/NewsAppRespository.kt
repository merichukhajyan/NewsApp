package com.example.newsapp.domain.repository

import com.example.newsapp.data.dto.NewsResponseDto

interface NewsRepository {
    suspend fun fetchNews(country: String): NewsResponseDto

    suspend fun fetchNewsByCategory(category: String): NewsResponseDto

    suspend fun fetchNewsByQuery(query: String) : NewsResponseDto
}