package com.example.crocusoft_mova.presentation.language.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.language.AppLanguage

@Composable
fun LanguageItem(
    language : AppLanguage,
    isSelected : Boolean,
    onClick : ()->Unit
){
    Row(modifier = Modifier.fillMaxWidth()
        .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Text(text = stringResource(language.titleRes),
            style = BaseTheme.textStyle.t18SemiBold)

        CustomRadioButton(isSelected = isSelected)

    }

}