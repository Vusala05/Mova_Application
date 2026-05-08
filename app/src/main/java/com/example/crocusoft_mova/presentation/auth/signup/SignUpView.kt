package com.example.crocusoft_mova.presentation.auth.signup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun SignUpView(innerPadding: PaddingValues, navController: NavController ,viewModel: SignUpViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    SignUpContent(
        state = state,
        postIntent = viewModel::onIntent,
        onNavigate = { navController.navigate(AppRoutes.AuthRoute.SignIn)},
        onNavigateBack = {navController.popBackStack()},
        onNavigateToChooseInterest = {navController.navigate(AppRoutes.AuthRoute.ChooseInterest)}  ,
        effect = viewModel.effect

    )
}

