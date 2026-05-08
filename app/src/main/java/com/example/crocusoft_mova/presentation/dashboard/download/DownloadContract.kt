package com.example.crocusoft_mova.presentation.dashboard.download

import com.example.crocusoft_mova.domain.models.MovieUiModel

sealed interface DownloadContract {


    sealed interface Intent {
        data object LoadMovies : Intent

    }

    data class State(
        val movies: List<MovieUiModel> = emptyList()
    )
}