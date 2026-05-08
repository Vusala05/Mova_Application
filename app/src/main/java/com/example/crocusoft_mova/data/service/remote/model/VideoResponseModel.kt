package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.Serializable

@Serializable
    data class VideoResponseModel(
        val id: Int? = null,
        val results: List<VideoModel>? = null
    )
