package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import com.example.crocusoft_mova.domain.repository.HomeRepository
import javax.inject.Inject

class FetchUpcomingMoviesUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend operator fun invoke(): ContentState<List<MovieUiModel>> {
        return repository.fetchUpcomingMovies()

    }
}