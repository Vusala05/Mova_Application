package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.domain.models.RegionUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import javax.inject.Inject

class FetchMovieGenresUseCase @Inject constructor(val repository: ExploreRepository) {
    suspend operator fun invoke() : ContentState<List<GenreUiModel>>{
        return repository.fetchMovieGenre()
    }
}