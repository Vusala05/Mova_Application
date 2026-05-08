package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponseModel(
    val genres: List<Genre>
)  {
}