package com.example.crocusoft_mova.presentation.auth.pin

sealed interface PinContract {

    sealed interface Intent {

        data class SetPin(val index: Int, val pin: String) : Intent

        data object EnterPin: Intent

        data object Submit : Intent
    }


    sealed interface Effect {
        data object NavigateHome : Effect
        data class ShowError(val message: String) : Effect
    }


    data class State(
        val pin: List<String> = List(4) { "" },
        val error : String = "",
    )
}