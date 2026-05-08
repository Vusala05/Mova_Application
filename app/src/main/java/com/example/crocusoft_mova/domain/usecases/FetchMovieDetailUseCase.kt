package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.repository.MovieDetailRepository
import javax.inject.Inject

class FetchMovieDetailUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend operator fun invoke(movieId: Int): ContentState<MovieDetailUiModel> {
        return movieDetailRepository.getMovieDetail(movieId)
    }
}