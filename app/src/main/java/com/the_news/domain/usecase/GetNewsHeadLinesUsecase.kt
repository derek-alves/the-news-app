package com.the_news.domain.usecase

import com.the_news.data.model.NewsResponse
import com.the_news.domain.repository.NewsRepository

class GetNewsHeadLinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(page: Int, category: String): Result<NewsResponse> {
        return newsRepository.getNewsHeadLines(page, category)
    }
}