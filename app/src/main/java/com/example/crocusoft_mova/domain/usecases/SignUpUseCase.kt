package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository) {

    suspend operator fun invoke(email: String, password: String) =
        repository.signUp(email, password)


}