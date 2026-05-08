package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import com.example.crocusoft_mova.domain.repository.HomeRepository
import jakarta.inject.Inject

class FetchDiscoverMovieUseCase @Inject constructor(
    private val repository: ExploreRepository
) /*{

    suspend operator fun invoke(region: String?, genre: String?, time: String?, sort: String): ContentState<List<MovieUiModel>> {
        return repository.fetchDiscoverMovies(
            region = region,
            genre = genre,
            time = time,
            sort = sort
        )
    }
}*/