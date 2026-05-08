package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import com.example.crocusoft_mova.domain.repository.SignInRepository
import javax.inject.Inject

class CheckIfUserExistUseCase @Inject constructor(private val repository: SignInRepository) {

    suspend operator fun invoke(): ContentState<Boolean> = repository.checkWhetherExist()
}