package com.example.crocusoft_mova.presentation.dashboard.movie_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.core.MovieCategoryType
import com.example.crocusoft_mova.ui.navigation.AppRoutes

@Composable
fun MovieListView(
    navController: NavController,
    categoryType: MovieCategoryType,
    viewModel : MovieListViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        viewModel.onIntent(MovieListContract.Intent.FetchMovies(categoryType = categoryType))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieListContent(
        effect = viewModel.effect,
        state = state,
        onBackClick = { navController.popBackStack()},
        navigateToDetail = { id, type ->
            if (type == "movie") {
                navController.navigate(AppRoutes.DashboardRoute.MovieDetail(id))
            }
        }
    )

}