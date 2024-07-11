package com.the_news.data.datasource

import com.the_news.data.model.ApiResponse
import com.the_news.data.model.Article
import com.the_news.data.model.Source
import com.the_news.infra.api.NewsAPIService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response


class NewsRemoteDatasourceTest {
    private lateinit var sut: NewsRemoteDatasource

    private lateinit var apiServiceMock: NewsAPIService

    private var articlesMock = listOf(
        Article(
            title = "any-title",
            author = "any-author",
            urlToImage = "any-url",
            url = "any-url",
            id = 0,
            source = Source("any-id", "any-name"),
            content = "any-content",
            description = "any-description",
            publishedAt = "27/03/2023"
        )
    )

    private var apiResponseMock =
        ApiResponse(status = "ok", totalResults = 10, articles = articlesMock)

    @Before
    fun setUp() {
        apiServiceMock = mock(NewsAPIService::class.java)
        sut = NewsRemoteDataSourceImpl(apiServiceMock)

    }


    @Test
    fun getTopHeadlines_returnNewsSuccess() {
        runBlocking {
            `when`(apiServiceMock.getTopHeadlines(page = 1, category = "any-category"))
                .thenReturn(Response.success(apiResponseMock))

            val response = sut.getTopHeadlines(page = 1, category = "any-category")
            assertEquals(response.data?.status, "ok")
        }
    }
}