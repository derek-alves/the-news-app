package com.the_news.data.datasource

import com.the_news.data.model.Article
import com.the_news.infra.db.ArticleDAO
import kotlinx.coroutines.flow.Flow

interface NewsLocalDatasource {
    suspend fun saveArticle(articles: Article)
    fun getSavedArticles(): Flow<List<Article>>
}


class NewsLocalDatasourceImpl(private val articleDAO: ArticleDAO) : NewsLocalDatasource {
    override suspend fun saveArticle(articles: Article) {
        articleDAO.insert(articles)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }
}