package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.ProfileUiModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun logOut(): ContentState<Unit>
    fun getCurrentUser(): Flow<ContentState<ProfileUiModel>>
}