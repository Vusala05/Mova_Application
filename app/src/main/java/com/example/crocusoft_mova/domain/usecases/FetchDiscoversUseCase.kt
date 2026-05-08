package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import javax.inject.Inject

class FetchDiscoversUseCase @Inject constructor(private val repository: ExploreRepository) {

    suspend operator fun invoke(category : String, region: String?, genre: String?, time: String?, sort: String): ContentState<List<MovieUiModel>> {
        return repository.fetchDiscovers(
            category = category,
            region = region,
            genre = genre,
            time = time,
            sort = sort
        )
    }
}