package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class VideoModel(
    val id: String? = null,
    val key: String? = null,
    val name: String? = null,
    val site: String? = null,
    val type: String? = null
)