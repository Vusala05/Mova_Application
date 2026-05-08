package com.example.crocusoft_mova.presentation.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.language.LanguageManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LanguageViewModel() : ViewModel() {

        private val _state = MutableStateFlow(LanguageContract.State())
        val state = _state.asStateFlow()


        fun onIntent(intent: LanguageContract.Intent) {
            when (intent) {
                is LanguageContract.Intent.SelectLanguageCode -> {
                    viewModelScope.launch {
                        _state.update { it.copy(isLoading = true) }
                        delay(500)
                        LanguageManager.changeLanguage(intent.context, intent.code)
                        updateLanguageState(intent.code, isLoading = false)
                    }
                }
                is LanguageContract.Intent.LoadCurrentLanguage -> {
                    val currentCode = LanguageManager.getCurrentLanguage(intent.context)
                    updateLanguageState(currentCode)
                }
            }
        }

        private fun updateLanguageState(code: String, isLoading: Boolean = false) {
            _state.update {
                it.copy(
                    selectedLanguageCode = code,
                    isLoading = isLoading
                )
            }
        }
    }