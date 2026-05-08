package com.example.crocusoft_mova.presentation.dashboard.movie_list

import com.example.crocusoft_mova.core.MovieCategoryType
import com.example.crocusoft_mova.domain.models.MovieUiModel

object MovieListContract {
     sealed interface Intent{
        data class FetchMovies(val categoryType: MovieCategoryType) : Intent
     }
    data class State(
        val title : String = "",
        val movieList : List<MovieUiModel> = emptyList(),
        val isLoading : Boolean = false
    )

    sealed interface Effect {
        data class ShowError(val message : String) : Effect
    }
}