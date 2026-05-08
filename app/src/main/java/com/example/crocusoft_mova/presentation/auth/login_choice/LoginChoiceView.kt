package com.example.crocusoft_mova.presentation.auth.login_choice


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun LoginChoiceView(navController: NavController) {


    LoginChoiceContent(
        onNavigateSignIn = { navController.navigate(AppRoutes.AuthRoute.SignIn) },
        onNavigateSignUp = { navController.navigate(AppRoutes.AuthRoute.SignUp) },
        onNavigateBack = navController::popBackStack
    )
}