package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverModel(
    @SerialName("id")
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = emptyList(),
     //Movie
    @SerialName("title")
    val title: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    //Tv
    @SerialName("name")
    val name: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("original_name")
    val originalName: String? = null
)