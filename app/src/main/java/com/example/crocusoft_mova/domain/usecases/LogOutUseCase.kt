package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.ProfileRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val profileRepository: ProfileRepository

) {

    suspend operator fun invoke() = profileRepository.logOut()
}