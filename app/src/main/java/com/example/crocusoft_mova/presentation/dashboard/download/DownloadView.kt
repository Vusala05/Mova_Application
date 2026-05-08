package com.example.crocusoft_mova.presentation.dashboard.download

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController


@Composable
fun DownloadView(navController: NavController, viewModel: DownloadViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    DownloadContent(
        state = state,
        postIntent = viewModel::onIntent
    )

}