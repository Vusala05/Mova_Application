package com.example.crocusoft_mova.presentation.language

import android.content.Context
import com.example.crocusoft_mova.core.language.AppLanguage

class LanguageContract {

    data class State(
        val languages : List<AppLanguage> = AppLanguage.entries,
        val selectedLanguageCode : String = AppLanguage.EN.code,
        val isLoading : Boolean = false
    )
    sealed interface Intent {
        data class SelectLanguageCode(val context: Context, val code : String) : Intent
        data class  LoadCurrentLanguage(val context : Context) : Intent
    }



}