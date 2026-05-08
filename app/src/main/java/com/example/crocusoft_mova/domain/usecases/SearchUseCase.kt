package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.ExploreRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ExploreRepository) {

    suspend operator fun invoke(query: String) = repository.search(query)

}
