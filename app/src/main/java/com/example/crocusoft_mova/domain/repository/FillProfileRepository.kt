package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState

interface FillProfileRepository {

    suspend fun fillProfile(
        fullName: String,
        gender: String,
        nickname: String,
        phoneNumber: String,
        profileUri: String,
    ): ContentState<Unit>
}