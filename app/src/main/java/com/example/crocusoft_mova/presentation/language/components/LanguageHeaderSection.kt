package com.example.crocusoft_mova.presentation.language.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors

@Composable
fun LanguageHeaderSection(title: String) {
    Text(
        text = title,
        style = BaseTheme.textStyle.t20Bold,
        color = colorResource(Colors.white),
        modifier = Modifier
            .fillMaxWidth()
    )
}