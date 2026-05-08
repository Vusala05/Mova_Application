package com.example.crocusoft_mova.presentation.dashboard.profile

import com.example.crocusoft_mova.domain.models.ProfileUiModel

sealed interface ProfileContract {

    sealed interface Intent {
        data object LoadProfile : Intent
        data object LogOut : Intent

    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
        data object NavigateSignChoice : Effect



    }

    data class State(
        val profile: ProfileUiModel = ProfileUiModel.empty,
        val isDarkMode : Boolean = false,
        val isLoading : Boolean = false
    )
}