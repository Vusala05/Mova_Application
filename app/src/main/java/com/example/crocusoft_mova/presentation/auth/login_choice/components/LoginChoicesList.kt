package com.example.crocusoft_mova.presentation.auth.login_choice.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings


@Composable
fun LoginChoicesList() {


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
    ) {
        AppIconButton(
            modifier = Modifier.fillMaxWidth(),
            icon = Drawables.facebook,
            textRes = Strings.continue_facebook
        ) { }
        AppIconButton(
            modifier = Modifier.fillMaxWidth(),
            icon = Drawables.google,
            textRes = Strings.continue_google
        ) { }
        AppIconButton(
            modifier = Modifier.fillMaxWidth(),
            icon = Drawables.apple,
            textRes = Strings.continue_apple
        ) { }
    }
}