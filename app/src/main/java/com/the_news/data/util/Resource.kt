package com.the_news.data.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null, code: Int) :
        Resource<T>(data, message, code)
}

fun <T> Resource<T>.isSuccess(): Boolean {
    return when (this) {
        is Resource.Success -> true
        is Resource.Error -> false
    }
}