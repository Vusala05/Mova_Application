package com.example.crocusoft_mova.presentation.dashboard.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

   HomeContent(
       state = state,
       onNavigateDetail = { navController.navigate(AppRoutes.DashboardRoute.MovieDetail(it),)
       },
       effect = viewModel.effect,
       onNavigateMovieList = {navController.navigate(AppRoutes.DashboardRoute.MovieList(it))}

   )
}