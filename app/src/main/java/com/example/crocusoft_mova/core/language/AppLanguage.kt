package com.example.crocusoft_mova.core.language

import androidx.annotation.StringRes
import com.example.crocusoft_mova.R
import com.example.crocusoft_mova.core.Strings

enum class AppLanguage(
    val code: String,
    @StringRes val titleRes: Int
) {

    EN("en", Strings.language_english),
    AZ("az", Strings.language_azerbaijani),
    TR("tr", Strings.language_turkish),
    RU("ru", Strings.language_russian);

    companion object {
        fun fromCode(code: String?): AppLanguage {
            return entries.find { it.code == code } ?: EN
        }

    }
}