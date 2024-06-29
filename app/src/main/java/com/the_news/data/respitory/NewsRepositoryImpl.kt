package com.the_news.data.respitory

import com.the_news.data.datasource.NewsLocalDatasource
import com.the_news.data.datasource.NewsRemoteDatasource
import com.the_news.data.model.Article
import com.the_news.data.model.NewsResponse
import com.the_news.data.util.isSuccess
import com.the_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsRemoteDatasource: NewsRemoteDatasource,
    private val newsLocalDatasource: NewsLocalDatasource
) : NewsRepository {
    override suspend fun getNewsHeadLines(
        page: Int,
        category: String,
    ): Result<NewsResponse> {
        val response = newsRemoteDatasource.getTopHeadlines(page, category)
        if (response.data == null) {
            return Result.failure(Exception("No data found"))
        }
        if (response.isSuccess()) {
            return Result.success(response.data)
        }
        return Result.failure(Exception("No data found"))
    }

    override suspend fun getSearchedNews(
        query: String,
        page: Int,
        category: String
    ): Result<NewsResponse> {
        val response = newsRemoteDatasource.getSearchedNews(
            page = page,
            category = category,
            searchQuery = query
        )
        if (response.data == null) {
            return Result.failure(Exception("No data found"))
        }
        if (response.isSuccess()) {
            return Result.success(response.data)
        }
        return Result.failure(Exception("No data found"))
    }


    override suspend fun saveNews(article: Article) {
        newsLocalDatasource.saveArticle(article)
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}