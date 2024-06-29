package com.the_news.presentation.utils

sealed class ErrorType(
) {
    data object NetWork : ErrorType()
    data object Unexpected : ErrorType()
}


