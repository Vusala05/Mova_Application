package com.example.crocusoft_mova.core

sealed class ContentState<T> {
    data class Success<T>(val data: T) : ContentState<T>()
    data class Error<T>(val message: String) : ContentState<T>()
}

