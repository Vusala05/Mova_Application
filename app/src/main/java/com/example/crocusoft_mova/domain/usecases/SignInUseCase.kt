package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: SignInRepository) {

    suspend operator fun invoke(email: String, password: String) =
        repository.signIn(email, password)

}