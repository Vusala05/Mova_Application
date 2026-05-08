package com.example.crocusoft_mova.data.mappers

import com.example.crocusoft_mova.data.service.remote.model.DiscoverModel
import com.example.crocusoft_mova.data.service.remote.model.Episode
import com.example.crocusoft_mova.data.service.remote.model.Genre
import com.example.crocusoft_mova.data.service.remote.model.MovieDetailModel
import com.example.crocusoft_mova.data.service.remote.model.MovieModel
import com.example.crocusoft_mova.data.service.remote.model.RegionModel
import com.example.crocusoft_mova.data.service.remote.model.SimilarMovieModel
import com.example.crocusoft_mova.data.service.remote.model.TvModel
import com.example.crocusoft_mova.data.service.remote.model.VideoModel
import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.ProductionCompany
import com.example.crocusoft_mova.domain.models.RegionUiModel
import com.example.crocusoft_mova.domain.models.SimilarUiModel
import com.example.crocusoft_mova.domain.models.TrailerUiModel
import kotlin.Int


fun MovieModel.toUiModel(): MovieUiModel = MovieUiModel(
    id = id,
    title = title ?: "",
    vote_average = vote_average ?: 0.0,
    release_date = release_date ?: "",
    description = original_title ?: "",
    image = poster_path ?: "",
    type = "movie"
)
/*fun DiscoverModel.toUiModel(mediaType: String) : MovieUiModel = MovieUiModel(
    id = id,
    title = title ?: name ?: "",
    vote_average = voteAverage ?: 0.0,
    release_date = releaseDate ?: firstAirDate ?: "",
    description = overview ?: "",
    image = posterPath ?: "",
    type = mediaType
)*/

fun TvModel.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        title = name ?: "",
        vote_average = voteAverage ?: 0.0,
        release_date = firstAirDate ?:"",
        description = overview ?:"",
        image = posterPath ?:"",
        type = "tv"
    )
}
fun MovieDetailUiModel.toMovieUiModel(): MovieUiModel = MovieUiModel(
    id = id,
    title = title,
    vote_average = vote_average,
    image = image,
    release_date = release_date,
    description = description,
    type = "movie"
)

fun MovieDetailModel.toUiModel(type: String): MovieDetailUiModel = MovieDetailUiModel(
    id = id ?: 0,
    title = title ?: "",
    release_date = release_date ?: "",
    description = overview ?: "",
    vote_average = vote_average ?: 0.0,
    image = poster_path ?: "",
    genres = genres?.map { it.name ?: "" } ?: emptyList(),
    production_companies = production_companies?.map {
        ProductionCompany(
            it.name ?: "",
            it.logo_path ?: ""
        )
    } ?: emptyList(),
    language = original_language ?: "",
    mediaType = type


)

fun VideoModel.toUiModel() : TrailerUiModel = TrailerUiModel(
    id = 0,
    title = name ?: "Unknown Trailer",
    site = site ?: "",
    key = key ?: "",
    duration = "1m 45s"
)

fun RegionModel.toUiModel() : RegionUiModel = RegionUiModel(
    englishName = englishName ?: "",
    isoCode = isoCode ?: ""
)

fun Genre.toUiModel() : GenreUiModel = GenreUiModel(
    id = id ?:0,
     name = name ?: ""
)

