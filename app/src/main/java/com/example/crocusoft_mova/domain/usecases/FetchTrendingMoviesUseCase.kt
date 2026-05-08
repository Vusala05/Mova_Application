package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import javax.inject.Inject

class FetchTrendingMoviesUseCase @Inject constructor(private val repository: ExploreRepository) {

    suspend operator fun invoke(): ContentState<List<MovieUiModel>> {
        return repository.fetchTrending()

    }
}