package com.example.crocusoft_mova.domain.models

import androidx.compose.runtime.key

data class TrailerUiModel(
    val id: Int,
    val title: String,
    val key : String,
    val site : String,
    val duration: String = "1m 45s"
){

    companion object{
        val trailer = TrailerUiModel(
            id = 0,
            title = "",
            key = "",
            site = "",
            duration = "1m 45s"
        )
    }
}

