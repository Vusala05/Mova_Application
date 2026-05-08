package com.example.crocusoft_mova

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.data.local.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    val isDarkModeActive = userPreferencesRepository.userPreferencesFlow.map { it.isDarkModeActive }

    fun updateIsDarkModeActive(isDarkModeActive : Boolean){
        viewModelScope.launch {
            userPreferencesRepository.updateIsDarkModeActive(isDarkModeActive)
        }
    }
}