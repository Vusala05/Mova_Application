package com.example.crocusoft_mova.presentation.auth.fill_profile

import android.net.Uri

sealed interface FillProfileContract {

    sealed interface Intent {
        data class SetFullName(val fullName: String) : Intent
        data class SetNickName(val nickName: String) : Intent
        data class SetPhoneNumber(val phoneNumber:String):Intent
        data class SetGender(val gender:String):Intent

        data class SetProfile(val uri:Uri):Intent

        data object Submit: Intent

        data object Skip:Intent
    }


    sealed interface Effect {
        data object NavigatePin: Effect

        data object NavigateProfile: Effect

        data class ShowError(val message:String):Effect
    }



    data class State(
        val fullName: String = "",
        val nickName: String = "",
        val phoneNumber:String = "",
        val gender: String = "",
        val imgUri: Uri? = null,
        val isEditMode: Boolean = false,
        val isLoading : Boolean = false
    )
}