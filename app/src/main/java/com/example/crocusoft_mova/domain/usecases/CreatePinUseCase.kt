package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.PinRepository
import javax.inject.Inject

class CreatePinUseCase @Inject constructor(private val repository: PinRepository) {

    suspend operator fun invoke(pin: String) = repository.createPin(pin)
}