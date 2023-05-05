package com.example.newsapp.data.remote

import com.example.newsapp.data.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("${ApiConstants.VERSION}/top-headlines?apiKey=${ApiConstants.API_KEY}")
    suspend fun fetchNews(@Query("country") country :String): NewsResponseDto

    @GET("${ApiConstants.VERSION}/top-headlines?apiKey=${ApiConstants.API_KEY}")
    suspend fun fetchNewsByCategory(@Query("category") category :String): NewsResponseDto

    @GET("${ApiConstants.VERSION}/top-headlines?apiKey=${ApiConstants.API_KEY}")
    suspend fun fetchNewsByQuery(@Query("q") query :String): NewsResponseDto



}