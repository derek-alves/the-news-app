package com.the_news.domain.usecase

import com.the_news.data.model.NewsResponse
import com.the_news.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(query: String, page: Int, category: String): Result<NewsResponse> {
        return newsRepository.getSearchedNews(query = query, page = page, category = category)
    }
}