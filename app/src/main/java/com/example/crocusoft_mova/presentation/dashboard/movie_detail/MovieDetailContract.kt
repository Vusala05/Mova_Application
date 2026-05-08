package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.TrailerUiModel

sealed interface MovieDetailContract {

    sealed interface Intent {
        data class FetchMovieDetail(val movieId: Int) : Intent
        data class OnTabSelected(val index: Int) : Intent
        data object OnBookMarkClick : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
    }

    data class State(
        val movieDetail: MovieDetailUiModel = MovieDetailUiModel.empty,
        val trailers: List<TrailerUiModel> = emptyList(),
        val similarMovies: List<MovieUiModel> = emptyList(),
        val comments : List<String> = emptyList(),
        val selectedTab: Int = 0,
        val isBookMarked : Boolean = false
    )
}