package com.the_news.domain.usecase

import com.the_news.data.model.ApiResponse
import com.the_news.data.model.Article
import com.the_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUsecase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}