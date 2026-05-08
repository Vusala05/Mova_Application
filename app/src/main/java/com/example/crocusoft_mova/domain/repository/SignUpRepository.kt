package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState

interface SignUpRepository {

    suspend fun signUp(email: String, password: String): ContentState<Unit>

}