package com.example.crocusoft_mova.presentation.dashboard.download

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DownloadViewModel : ViewModel() {

    private val _state = MutableStateFlow(DownloadContract.State())
    val state = _state.asStateFlow()

    fun onIntent(intent: DownloadContract.Intent) {
        when (intent) {
            DownloadContract.Intent.LoadMovies -> {}
        }
    }
}