package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.TrailerUiModel
import com.example.crocusoft_mova.domain.repository.MovieDetailRepository
import jakarta.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): ContentState<MovieDetailUiModel> = try {
        val res = apiService.fetchMovieDetail(movieId)
        ContentState.Success(res.toUiModel(type = "Movie"))


    } catch (e: Exception) {
        ContentState.Error(e.message ?: AppErrors.unknownError)
    }

    override suspend fun getSimilarMovies(movieId: Int): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchSimilarMovies(movieId)
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }



    override suspend fun getMovieVideos(movieId: Int): ContentState<List<TrailerUiModel>> =
        try {
            val res = apiService.fetchMoviesVideos(movieId)
            val results = res.results
            if(results.isNullOrEmpty()){
                ContentState.Error(AppErrors.noVideos)
            } else{
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)

        }

}
