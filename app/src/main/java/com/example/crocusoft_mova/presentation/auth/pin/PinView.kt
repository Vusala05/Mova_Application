package com.example.crocusoft_mova.presentation.auth.pin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun PinView(
    navController: NavController,
    isNewPinScreen: Boolean = true,
    viewModel: PinViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    PinContent(
        state = state,
        postIntent = viewModel::onIntent,
        effect = viewModel.effect,
        isNewPinScreen = isNewPinScreen,
        onNavigateHome = {
            navController.navigate(AppRoutes.DashboardRoute.Home) {
                popUpTo(0) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        },
        onNavigateBack = navController::popBackStack,

        )

}
