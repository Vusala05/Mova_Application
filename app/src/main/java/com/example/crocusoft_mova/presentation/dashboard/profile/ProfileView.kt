package com.example.crocusoft_mova.presentation.dashboard.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun ProfileView(
    navController: NavController,
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

   /* LaunchedEffect(Unit) {
        viewModel.onIntent(ProfileContract.Intent.LoadProfile)
    }*/

    ProfileContent(
        state = state,
        effect = viewModel.effect,
        postIntent = viewModel::onIntent,

        onNavigateSignChoice = {
            navController.navigate(AppRoutes.AuthRoute.Splash) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        },

        onNavigateToLanguage = {
            navController.navigate(AppRoutes.DashboardRoute.Language)
        },

        onNavigateFillProfile = {
            navController.navigate(AppRoutes.AuthRoute.FillProfile(it))
        },

        isDarkMode = isDarkMode,
        onDarkModeChange = onDarkModeChange
    )
}