package com.example.crocusoft_mova.presentation.auth.fill_profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun FillProfileView(
    viewModel: FillProfileViewModel = hiltViewModel(),
    isEdit : Boolean,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.setEditMode(isEdit)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    FillProfileContent(
        state = state,
        postIntent = viewModel::onIntent,
        onNavigateBack = {navController.popBackStack()},
        effect = viewModel.effect,
        onNavigatePin = {navController.navigate(AppRoutes.AuthRoute.NewPin)}
    )
}