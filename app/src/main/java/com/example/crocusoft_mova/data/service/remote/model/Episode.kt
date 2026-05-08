package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class Episode(
    val adult: Boolean?=null,
    val backdrop_path: String?=null,
    val genre_ids: List<Int>?=null,
    val id: Int?=null,
    val media_type: String?=null,
    val name: String?=null,
    val original_language: String?=null,
    val original_name: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    val poster_path: String?=null,
    val release_date: String?=null,
    val video: Boolean?=null,
    val vote_average: Double?=null,
    val vote_count: Int?=null
)