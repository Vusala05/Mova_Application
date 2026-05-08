package com.example.crocusoft_mova.presentation.auth.signup

sealed interface SignUpContract {

    sealed interface Intent {

        data object Submit : Intent

        data class SetEmail(val email: String) : Intent

        data class SetPassword(val password: String) : Intent

        data class SetChecked(val checked: Boolean) : Intent


    }

    sealed interface UiEffect {
        data object NavigateToChoose : UiEffect
        data class ShowError(val message: String) : UiEffect
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val checked: Boolean = false,
        val isLoading: Boolean = false,

        )
}