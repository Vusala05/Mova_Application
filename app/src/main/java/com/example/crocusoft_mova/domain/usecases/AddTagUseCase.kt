package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import javax.inject.Inject

class AddTagUseCase @Inject constructor(private val repository: ChooseInterestRepository) {

    suspend operator fun invoke(tags: List<String>) = repository.addTags(tags)
}