package com.example.crocusoft_mova.presentation.dashboard.my_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun MyListView(navController: NavController, viewModel: MyListViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    MyListContent(
        state = state,
        postIntent = viewModel::onIntent,
        effect = viewModel.effect,
        onNavigateToDetail = {navController.navigate(AppRoutes.DashboardRoute.MovieDetail(it))}
    )

}