package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.PinRepository
import javax.inject.Inject

class EnterPinUseCase @Inject constructor(private val pinRepository: PinRepository) {

    suspend operator fun invoke(pin: String): ContentState<Unit> {
        return pinRepository.enterPin(pin)
    }
}