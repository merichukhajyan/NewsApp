package com.example.newsapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

data class Source(
    val id: String?,
    val name: String
)
@Parcelize
 class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: @RawValue Source,
    val title: String,
    val url: String,
    val urlToImage: String?
) : Parcelable