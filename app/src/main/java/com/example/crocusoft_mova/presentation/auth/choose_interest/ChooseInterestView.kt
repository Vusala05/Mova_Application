package com.example.crocusoft_mova.presentation.auth.choose_interest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun ChooseInterestView(
    navController: NavController,
    viewModel: ChooseInterestViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ChooseInterestContent(
        onNavigateBack = { navController.popBackStack() },
        state = state,
        postIntent = viewModel::onIntent,
        effect = viewModel.effect,
        onNavigateFillProfile = { navController.navigate(AppRoutes.AuthRoute.FillProfile(false)) },
        onNavigateHome = { navController.navigate(AppRoutes.DashboardRoute.Home) }
    )

}