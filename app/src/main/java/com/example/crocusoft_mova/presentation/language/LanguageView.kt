package com.example.crocusoft_mova.presentation.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.presentation.language.LanguageViewModel

@Composable
fun LanguageView(
    viewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController
){
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onIntent(LanguageContract.Intent.LoadCurrentLanguage(context))
    }
    LanguageContent(
        state = state,
        postIntent = viewModel::onIntent,
        onBackClick = { navController.popBackStack() }
    )

}