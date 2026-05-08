package com.example.crocusoft_mova.data.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService) : HomeRepository {
    override suspend fun fetchNowPlayingMovies(): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchNowPlaying()
           setMovieUi(res)
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)

        }

    override suspend fun fetchTopRatedMovies(): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchTopRatedMovies()
            setMovieUi(res)
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }


    override suspend fun fetchUpcomingMovies(): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchUpcomingMovies()
            setMovieUi(res)
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }


    private fun setMovieUi(data: ResponseModel<MovieModel>): ContentState<List<MovieUiModel>> =
        try {
            val results = data.results
        if (results.isNullOrEmpty()) {
            ContentState.Error(AppErrors.noMovies)
        } else {
            ContentState.Success(results.map { it.toUiModel() })
        }
    } catch (e: Exception) {
        ContentState.Error(e.message ?: AppErrors.unknownError)
    }
}
