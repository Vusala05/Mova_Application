package com.example.crocusoft_mova.domain.models

data class ProfileUiModel(
    val profileId: String,
    val email: String,
    val fullName: String,
    val imageUri:String,
    val nickName : String = "",
    val phoneNumber : String = "",
    val gender : String = ""
) {
    companion object {
        val empty = ProfileUiModel(
            profileId = "",
            email = "",
            fullName = "",
            imageUri = ""

        )

        val mock = ProfileUiModel(
            profileId = "",
            email = "email@example.com",
            fullName = "Full Name",
            imageUri = ""

        )
    }
}
