package com.example.crocusoft_mova.presentation.auth.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun SignInView(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsStateWithLifecycle()


    SignInContent(

        postIntent = viewModel::onIntent,
        state = state,
        onNavigateSignUp = { navController.navigate(AppRoutes.AuthRoute.SignUp) },
        onNavigateBack = { navController.popBackStack() },
        onNavigateToChooseInterest = { navController.navigate(AppRoutes.AuthRoute.ChooseInterest) },
        effect = viewModel.effect,
        onNavigateHome = { navController.navigate(AppRoutes.DashboardRoute.Home) }
    )
}