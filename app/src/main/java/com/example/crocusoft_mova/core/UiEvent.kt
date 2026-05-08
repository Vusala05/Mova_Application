package com.example.crocusoft_mova.core

sealed class UiEvent {
    object ShowLoading : UiEvent()
    object HideLoading : UiEvent()
}