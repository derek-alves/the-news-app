package com.the_news.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.the_news.data.model.Article
import com.the_news.data.model.NewsResponse
import com.the_news.domain.usecase.GetNewsHeadLinesUseCase
import com.the_news.domain.usecase.GetSearchedNewsUseCase
import com.the_news.domain.usecase.SaveNewsUseCase
import com.the_news.presentation.utils.ErrorType
import com.the_news.presentation.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
) : AndroidViewModel(app) {
    val newsHeadLine: MutableLiveData<ViewState<NewsResponse>> = MutableLiveData()
    val searchedNews: MutableLiveData<ViewState<NewsResponse>> = MutableLiveData()
    private var newsHeadlinesList = mutableListOf<Article>()

    fun getNewsHeadlines(category: String, page: Int) {
        try {
            if (isNetworkAvailable(app)) {
                viewModelScope.launch(Dispatchers.IO) {
                    newsHeadLine.postValue(ViewState.Loading())
                    val result = getNewsHeadLinesUseCase.execute(page, category)


                    result.onSuccess { it ->

                        it.articles.let { newsHeadlinesList.addAll(it) }
                        it.articles = newsHeadlinesList
                        newsHeadLine.postValue(ViewState.Success(it))
                    }

                    result.onFailure { it ->
                        newsHeadLine.postValue(
                            ViewState.Error(
                                message = it.message,
                                type = ErrorType.Unexpected
                            )
                        )
                    }

                }
                return
            }
            newsHeadLine.postValue(ViewState.Error(type = ErrorType.NetWork))
        } catch (e: Exception) {
            newsHeadLine.postValue(
                ViewState.Error(
                    message = e.message,
                    type = ErrorType.Unexpected
                )
            )
        }


    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }

                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }

        return false

    }

    fun searchNews(searchQuery: String, page: Int, category: String) = viewModelScope.launch {
        try {
            searchedNews.postValue(ViewState.Loading())
            if (isNetworkAvailable(app)) {
                val response =
                    getSearchedNewsUseCase.execute(
                        page = page,
                        query = searchQuery,
                        category = category
                    )
                response.onSuccess { it ->

                    searchedNews.postValue(ViewState.Success(it))
                }

                response.onFailure { it ->
                    searchedNews.postValue(
                        ViewState.Error(
                            message = it.message,
                            type = ErrorType.Unexpected
                        )
                    )
                }
            } else {
                searchedNews.postValue(ViewState.Error(type = ErrorType.NetWork))
            }
        } catch (e: Exception) {
            searchedNews.postValue(
                ViewState.Error(
                    message = e.message,
                    type = ErrorType.Unexpected
                )
            )
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }
}