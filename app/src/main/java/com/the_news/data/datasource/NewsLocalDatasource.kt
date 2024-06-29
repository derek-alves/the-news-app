package com.the_news.data.datasource

import com.the_news.data.model.Article
import com.the_news.infra.db.ArticleDAO

interface NewsLocalDatasource {
    suspend fun saveArticle(articles: Article)
}


class NewsLocalDatasourceImpl(private val articleDAO: ArticleDAO) : NewsLocalDatasource {
    override suspend fun saveArticle(articles: Article) {
        articleDAO.insert(articles)
    }
}