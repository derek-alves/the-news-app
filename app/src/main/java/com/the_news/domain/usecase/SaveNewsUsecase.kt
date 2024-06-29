package com.the_news.domain.usecase

import com.the_news.data.model.Article
import com.the_news.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) {
        return newsRepository.saveNews(article)
    }
}