package com.example.crocusoft_mova.data.repository

import android.util.Log
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.constants.AppErrors
import com.example.crocusoft_mova.data.mappers.toUiModel
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.RegionUiModel
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ExploreRepository {
    override suspend fun search(query: String): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.searchMovie(query)
            Log.i("movies", res.page.toString())
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

    override suspend fun fetchTrending(): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchTrending()
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }

        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

    override suspend fun fetchRegions(): ContentState<List<RegionUiModel>> =
        try {
            val results = apiService.fetchRegions()
            if (results.isEmpty()) {
                ContentState.Error(AppErrors.noCountries)
            } else {
                val uiList = results.map { it.toUiModel() }.toMutableList()
                uiList.add(0, RegionUiModel(isoCode = "", englishName = "All Regions"))
                ContentState.Success(uiList)
            }

        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

    override suspend fun fetchMovieGenre(): ContentState<List<GenreUiModel>> =
        try {
            val res = apiService.fetchMovieGenres()
            val results = res.genres
            if (results.isEmpty()) {
                ContentState.Error(AppErrors.noGenres)
            } else {
                val uiList = results.map { it.toUiModel() }.toMutableList()
                uiList.add(0, GenreUiModel(-1, name = "All Genres"))
                ContentState.Success(uiList)
            }

        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

    override suspend fun fetchTvGenre(): ContentState<List<GenreUiModel>> =
        try {
            val res = apiService.fetchTvGenres()
            val results = res.genres
            if (results.isEmpty()) {
                ContentState.Error(AppErrors.noGenres)
            } else {
                val uiList = results.map { it.toUiModel() }.toMutableList()
                uiList.add(0, GenreUiModel(-1, name = "All Genres"))
                ContentState.Success(uiList)
            }

        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

   /* override suspend fun fetchDiscoverMovies(
        region: String?,
        genre: String?,
        time: String?,
        sort: String
    ): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchDiscoverMovies(region, genre, time, sort)
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }

    override suspend fun fetchDiscoverTvs(
        region: String?,
        genre: String?,
        time: String?,
        sort: String
    ): ContentState<List<MovieUiModel>> =

        try {
            val res = apiService.fetchDiscoverTv(region, genre, time, sort)
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }
*/
   override suspend fun fetchDiscovers(category: String, region: String?, genre: String?, time: String?, sort: String
   ): ContentState<List<MovieUiModel>> =
       try {
           val res  = when(category){
               "movie" -> apiService.fetchDiscoverMovies(region,genre,time,sort)
                   .results?.map { it.toUiModel() }
               else -> apiService.fetchDiscoverTv(region,genre,time,sort)
                   .results?.map { it.toUiModel() }
           }
           if(res.isNullOrEmpty()) ContentState.Error(AppErrors.noMovies)
           else ContentState.Success(res)
       } catch (e: Exception) {
           ContentState.Error(e.message ?: AppErrors.unknownError)
       }

    /*override suspend fun fetchDiscovers(category: String, region: String?, genre: String?, time: String?, sort: String
    ): ContentState<List<MovieUiModel>> =
        try {
            val res = apiService.fetchDiscovers(category,region, genre, time, sort)
            val results = res.results
            if (results.isNullOrEmpty()) {
                ContentState.Error(AppErrors.noMovies)
            } else {
                ContentState.Success(results.map { it.toUiModel(category) })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }*/

    }






