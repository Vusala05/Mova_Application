package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.FillProfileRepository
import javax.inject.Inject

class FillProfileUseCase @Inject constructor(
    private val repository: FillProfileRepository
) {

    suspend operator fun invoke(params: Params): ContentState<Unit> {
        return repository.fillProfile(
            fullName = params.fullName,
            gender = params.gender,
            nickname = params.nickname,
            phoneNumber = params.phoneNumber,
            profileUri = params.profileUri
        )

    }

    data class Params(
        val fullName: String,
        val gender: String,
        val nickname: String,
        val phoneNumber: String,
        val profileUri: String,
    )
}