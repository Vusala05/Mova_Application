package com.example.crocusoft_mova.data.service.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class ProductionCountry(
    val iso_3166_1: String? = null,
    val name: String? = null
)