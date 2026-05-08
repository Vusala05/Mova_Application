package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    @SerialName("adult")
    val adult: Boolean? = false,
    @SerialName("genre_ids")
    val genre_ids: List<Int>? = emptyList(),
    @SerialName("id")
    val id: Int,

    @SerialName("backdrop_path")
    val backdrop_path: String? = null,

    @SerialName("parts")
    val parts: List<Episode>? = null,

    @SerialName("original_language")
    val original_language: String? = null,
    @SerialName("original_title")
    val original_title: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = 0.0,
    @SerialName("poster_path")
    val poster_path: String? = null,
    @SerialName("release_date")
    val release_date: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: Boolean? = false,
    @SerialName("vote_average")
    val vote_average: Double? = 0.0,

    @SerialName("vote_count")
    val vote_count: Int? = 0,
)
