package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegionModel(
    @SerialName("iso_3166_1")
    val isoCode: String? = null,
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("native_name")
    val nativeName: String? = null
)