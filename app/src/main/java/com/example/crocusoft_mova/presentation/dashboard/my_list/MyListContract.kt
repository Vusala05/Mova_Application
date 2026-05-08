package com.example.crocusoft_mova.presentation.dashboard.my_list

import com.example.crocusoft_mova.domain.models.MovieDetailUiModel

sealed interface MyListContract {

    sealed interface Intent{
     object GetMarkedMovies : Intent
        data class OnSelectCategory(val category : String) : Intent
        data class OnMovieClick(val movieId: Int) : Intent
    }

    data class State(
        val movies:List<MovieDetailUiModel> = emptyList(),
        val filteredMovies : List<MovieDetailUiModel> = emptyList(),
        val categories : List<String> = listOf("All Categories", "Movie","Tv-Series","K-Drama"),
        val selectedCategory : String = "All Categories",
        val isLoading : Boolean = false
    )

    sealed interface Effect {
        data class ShowError (val message : String) : Effect
        data class NavigateToDetail(val movieId : Int ) : Effect
    }
}