package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.RegionUiModel

interface ExploreRepository {

    suspend fun search(query: String): ContentState<List<MovieUiModel>>

    suspend fun fetchTrending(): ContentState<List<MovieUiModel>>

    suspend fun fetchRegions(): ContentState<List<RegionUiModel>>

    suspend fun fetchMovieGenre(): ContentState<List<GenreUiModel>>

    suspend fun fetchTvGenre(): ContentState<List<GenreUiModel>>

   // suspend fun fetchDiscoverMovies(region: String?,genre: String?,time:String?,sort:String): ContentState<List<MovieUiModel>>

    //suspend fun fetchDiscoverTvs(region: String?,genre: String?,time:String?,sort:String): ContentState<List<MovieUiModel>>

    suspend fun fetchDiscovers(category : String ,region: String?,genre: String?,time:String?,sort:String) : ContentState<List<MovieUiModel>>
}