package com.example.crocusoft_mova.presentation.auth.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes

@Composable
fun OnboardingView(navController: NavController) {

    OnboardingContent(
        onNavSignChoice = {navController.navigate(AppRoutes.AuthRoute.SignChoice)}
    )
}
