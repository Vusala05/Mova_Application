package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.MovieCategoryType
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.HomeRepository
import javax.inject.Inject

class GetMoviesByCategoryUseCase @Inject constructor(val repository : HomeRepository) {

    suspend operator fun invoke( category : MovieCategoryType) : ContentState<List<MovieUiModel>>{
        return when(category){
            MovieCategoryType.NEW_RELEASES -> repository.fetchUpcomingMovies()
            MovieCategoryType.TOP_RATED -> repository.fetchTopRatedMovies()
        }
    }
}