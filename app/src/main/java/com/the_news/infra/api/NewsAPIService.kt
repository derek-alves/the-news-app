package com.the_news.infra.api

import com.the_news.BuildConfig
import com.the_news.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val CATEGORY = "general"
private const val COUNTRY = "us"

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = COUNTRY,
        @Query("page") page: Int,
        @Query("category") category: String = CATEGORY,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<ApiResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country") country: String = COUNTRY,
        @Query("page") page: Int,
        @Query("q") searchQuery: String,
        @Query("category") category: String = CATEGORY,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<ApiResponse>
}

