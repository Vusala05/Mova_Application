package com.example.crocusoft_mova.presentation.dashboard.home

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface HomeContract {


    sealed interface Intent {
        object FetchNowPlayingMovies : Intent
        object FetchUpcomingMovies : Intent
        object FetchTopRatedMovies : Intent
    }

    sealed interface Effect{
        data class ShowError(val message:String): Effect
    }


    data class State(
        val nowPlayingMovies: List<MovieUiModel> = emptyList(),
        val upcomingMovies : List<MovieUiModel> = emptyList(),
        val topRatedMovies : List<MovieUiModel> = emptyList(),
        val isNowPlayingLoading: Boolean = false,
        val isUpcomingLoading: Boolean = false,
        val isTopRatedLoading: Boolean = false
    )

}
