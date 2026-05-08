package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = emptyList(),
    @SerialName("popularity")
    val popularity: Double? = 0.0,
    @SerialName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerialName("vote_count")
    val voteCount: Int? = 0,
    @SerialName("origin_country")
    val originCountry: List<String>? = emptyList()
)