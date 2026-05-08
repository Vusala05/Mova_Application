package com.example.crocusoft_mova.presentation.auth.signup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Drawables


@Composable
fun SignUpWithList(){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {

        AppIconButton(icon = Drawables.facebook) { }

        AppIconButton(icon = Drawables.google) { }

        AppIconButton(icon = Drawables.apple) { }
    }
}