package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState

interface PinRepository {

    suspend fun createPin(pin: String): ContentState<Unit>

    suspend fun enterPin(pin:String): ContentState<Unit>
}