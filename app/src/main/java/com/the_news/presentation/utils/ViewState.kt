package com.the_news.presentation.utils

sealed class ViewState<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : ViewState<T>(data)
    class Loading<T>() : ViewState<T>()
    class Error<T>(message: String? = null, type: ErrorType = ErrorType.Unexpected) :
        ViewState<T>(null, message)
}

fun <T> ViewState<T>.isSuccess(): Boolean {
    return when (this) {
        is ViewState.Success -> true
        is ViewState.Error -> false
        is ViewState.Loading -> false
    }
}