package com.example.crocusoft_mova.data.service.remote

import com.example.crocusoft_mova.data.service.remote.model.DiscoverModel
import com.example.crocusoft_mova.data.service.remote.model.Genre
import com.example.crocusoft_mova.data.service.remote.model.GenreResponseModel
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.data.service.remote.model.RegionModel
import com.example.crocusoft_mova.data.service.remote.model.ResponseModel
import com.example.crocusoft_mova.data.service.remote.model.TvModel
import com.example.crocusoft_mova.data.service.remote.model.VideoResponseModel

interface ApiService {


    suspend fun fetchDiscoverMovies(region: String?,genre: String?,time:String?,sort:String): ResponseModel<MovieModel>

    suspend fun fetchDiscoverTv(region: String?,genre: String?,time:String?,sort:String): ResponseModel<TvModel>

    suspend fun fetchSimilarMovies(movieId : Int): ResponseModel<MovieModel>

    suspend fun fetchMoviesVideos(movieId : Int): VideoResponseModel

    suspend fun fetchNowPlaying(): ResponseModel<MovieModel>

    //suspend inline fun fetchDiscovers <reifed T>(category : String ,region: String?,genre: String?,time:String?,sort:String) : ResponseModel<DiscoverModel>

    suspend fun fetchRegions() : List<RegionModel>

    suspend fun fetchMovieGenres() : GenreResponseModel

    suspend fun fetchTvGenres() : GenreResponseModel

    suspend fun searchMovie(query: String): ResponseModel<MovieModel>

    suspend fun fetchTrending(): ResponseModel<MovieModel>

    suspend fun fetchMovieDetail(movieId: Int): MovieDetailModel

    suspend fun fetchTopRatedMovies() : ResponseModel<MovieModel>

    suspend fun fetchUpcomingMovies() : ResponseModel<MovieModel>


}