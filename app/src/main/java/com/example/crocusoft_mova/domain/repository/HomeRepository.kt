package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieUiModel

interface HomeRepository {

    suspend fun fetchNowPlayingMovies(): ContentState<List<MovieUiModel>>

    suspend fun fetchTopRatedMovies(): ContentState<List<MovieUiModel>>

    suspend fun fetchUpcomingMovies() : ContentState<List<MovieUiModel>>
}