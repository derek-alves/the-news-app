package com.the_news.data.datasource

import com.the_news.data.model.NewsResponse
import com.the_news.data.util.Resource
import com.the_news.infra.api.NewsAPIService


interface NewsRemoteDatasource {
    suspend fun getTopHeadlines(
        page: Int,
        category: String,
    ): Resource<NewsResponse>

    suspend fun getSearchedNews(
        searchQuery: String,
        page: Int,
        category: String,
    ): Resource<NewsResponse>
}

class NewsRemoteDataSourceImpl(private val apiService: NewsAPIService) : NewsRemoteDatasource {
    override suspend fun getTopHeadlines(
        page: Int,
        category: String,
    ): Resource<NewsResponse> {
        val response = apiService.getTopHeadlines(page = page, category = category)

        response.code()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                val mappedList: NewsResponse = NewsResponse(
                    articles = result.articles,
                    totalResults = result.totalResults,
                    status = result.status
                )

                return Resource.Success(mappedList)
            }

        }
        return Resource.Error(response.message(), code = response.code())
    }

    override suspend fun getSearchedNews(
        searchQuery: String,
        page: Int,
        category: String
    ): Resource<NewsResponse> {
        val response = apiService.getSearchedTopHeadlines(
            page = page,
            searchQuery = searchQuery,
            category = category
        )

        response.code()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                val mappedList: NewsResponse = NewsResponse(
                    articles = result.articles,
                    totalResults = result.totalResults,
                    status = result.status
                )

                return Resource.Success(mappedList)
            }

        }
        return Resource.Error(response.message(), code = response.code())
    }


}