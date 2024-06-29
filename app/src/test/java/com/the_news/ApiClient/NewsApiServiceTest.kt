package com.the_news.ApiClient

import com.google.common.truth.Truth.assertThat
import com.the_news.infra.api.NewsAPIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {

    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)


    }

    private fun enqueueMockResponse(
        fileName: String = "newsResponse.json"
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse()
            service.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&category=general&apiKey=c16e17d44ffe4fe98f427f135369dd9b")

        }
    }


    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articlesList = responseBody!!.articles
            val article = articlesList[0]
            assertThat(article.author).isEqualTo("Monitor do Mercado")
            assertThat(article.publishedAt).isEqualTo("2024-06-08T19:40:00Z")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(20)

        }
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}