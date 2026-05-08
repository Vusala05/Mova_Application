package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SimilarMovieModel(
    val id: Int? = null,
    val title: String? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val vote_average: Double? = null,
    val release_date: String? = null,
    val overview: String? = null
)