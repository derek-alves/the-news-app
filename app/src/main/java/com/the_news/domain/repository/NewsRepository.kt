package com.the_news.domain.repository

import com.the_news.data.model.Article
import com.the_news.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadLines(
        page: Int,
        category: String,
    ): Result<NewsResponse>

    suspend fun getSearchedNews(query: String, page: Int, category: String): Result<NewsResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>

}