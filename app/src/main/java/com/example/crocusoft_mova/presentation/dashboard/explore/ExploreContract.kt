package com.example.crocusoft_mova.presentation.dashboard.explore

import com.example.crocusoft_mova.domain.models.GenreUiModel
import com.example.crocusoft_mova.domain.models.MovieUiModel
import com.example.crocusoft_mova.domain.models.RegionUiModel
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract.State.Companion.DEFAULT_CATEGORY
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract.State.Companion.DEFAULT_GENRE
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract.State.Companion.DEFAULT_REGION
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract.State.Companion.DEFAULT_SORT
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract.State.Companion.DEFAULT_TIME
import com.example.crocusoft_mova.presentation.dashboard.explore.util.CategoryType
import com.example.crocusoft_mova.presentation.dashboard.explore.util.SortOption

sealed interface ExploreContract {
    sealed interface Intent {
        data class SetQuery(val query: String) : Intent
        data class ToggleFilterSheet(val visibility : Boolean) : Intent

        data class SelectCategory(val category: String) : Intent
        data class SelectRegion(val region: String) : Intent
        data class SelectGenre(val genre: String) : Intent
        data class SelectPeriod(val period: String) : Intent
        data class SelectSort(val sort: String) : Intent

        data object ApplyFilters : Intent
        data object ResetFilters : Intent

    }

    sealed interface Effect {
        data class ShowError(val message:String): Effect

    }

    data class State(
        val searchQuery: String = "",
        val movies: List<MovieUiModel> = emptyList(),
        val isBottomSheetVisible : Boolean = false,
        val isLoading : Boolean = false,

        val categories: List<String> = emptyList(),
        val regions: List<RegionUiModel> = emptyList(),
        val movieGenres: List<GenreUiModel> = emptyList(),
        val tvGenres: List<GenreUiModel> = emptyList(),
        val periods: List<String> = emptyList(),
        val sortOptions: List<String> = emptyList(),

        val selectedCategory: String = DEFAULT_CATEGORY,
        val selectedRegion: String = DEFAULT_REGION,
        val selectedGenre: String = DEFAULT_GENRE,
        val selectedPeriod: String = DEFAULT_TIME,
        val selectedSort: String = DEFAULT_SORT

    ){
        companion object {
            const val DEFAULT_CATEGORY = "Movie"
            const val DEFAULT_REGION = "All Regions"
            const val DEFAULT_GENRE = "All Genres"
            const val DEFAULT_SORT = "Popularity"
            const val DEFAULT_TIME = "All Periods"

        }
        val currentGenresToDisplay: List<GenreUiModel>
            get() = if (selectedCategory == CategoryType.MOVIE.displayName) movieGenres else tvGenres
    }
}
fun ExploreContract.State.isAnyFilteredApplied() : Boolean{
    return selectedCategory != DEFAULT_CATEGORY ||
            selectedRegion != DEFAULT_REGION ||
            selectedGenre != DEFAULT_GENRE ||
            selectedPeriod != DEFAULT_TIME ||
            selectedSort != DEFAULT_SORT
}

fun ExploreContract.State.selectedFilterTags() : List<String>{
    return listOf(selectedCategory,selectedRegion,selectedGenre,selectedPeriod,selectedSort)
}
fun ExploreContract.State.getGenreId(): String? {
     return currentGenresToDisplay.find { it.name == selectedGenre }?.id?.let {
        if (it == -1) null else it.toString()
    }
}

fun ExploreContract.State.getRegionCode(): String? {
    return regions.find { it.englishName == selectedRegion }?.isoCode?.takeIf { it.isNotEmpty() }
}


fun ExploreContract.State.getApiSortOrder(): String {
   return SortOption.entries.find { it.displayName == selectedSort}?.apiValue
       ?: SortOption.POPULARITY.apiValue
}

