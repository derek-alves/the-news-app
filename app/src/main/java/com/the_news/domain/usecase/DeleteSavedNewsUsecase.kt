package com.thenews.domain.usecase

import com.the_news.data.model.ApiResponse
import com.the_news.data.model.Article
import com.the_news.domain.repository.NewsRepository

class DeleteSavedNewsUsecase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article){
        return newsRepository.deleteNews(article)
    }
}