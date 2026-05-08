package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState

interface SignInRepository {


    suspend fun checkWhetherExist(): ContentState<Boolean>
    suspend fun signIn(email: String, password: String): ContentState<Unit>
}