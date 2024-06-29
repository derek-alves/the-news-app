package com.the_news.data.model

data class NewsResponse(
    val totalResults: Int,
    val status: String,
    var articles: List<Article>
)