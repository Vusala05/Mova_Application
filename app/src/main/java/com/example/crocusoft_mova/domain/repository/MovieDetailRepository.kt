package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.TrailerUiModel

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int): ContentState<MovieDetailUiModel>

    suspend fun getSimilarMovies(movieId: Int): ContentState<List<MovieUiModel>>

    suspend fun getMovieVideos(movieId: Int): ContentState<List<TrailerUiModel>>

}