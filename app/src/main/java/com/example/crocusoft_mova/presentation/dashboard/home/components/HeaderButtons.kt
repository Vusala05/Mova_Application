package com.example.crocusoft_mova.presentation.dashboard.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings

@Composable
fun HeaderButtons(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
        modifier = modifier
            .fillMaxWidth()
            .padding(BaseTheme.dimens.dp6)
    ) {
        AppIconButton(
            color = Colors.secondary,
            borderColor = Colors.secondary,
            icon = Drawables.icon_play,
            textRes = Strings.play,
            radius = BaseTheme.dimens.radius,
            iconSize = BaseTheme.dimens.dp3,
            horizontalPadding = BaseTheme.dimens.dp4,
            verticalPadding = BaseTheme.dimens.dp06
        ) {}

        AppIconButton(
            color = Colors.transparent,
            borderColor = Colors.white,
            icon = Drawables.icon_plus,
            iconSize = BaseTheme.dimens.dp3,
            textRes = Strings.my_list,
            radius = BaseTheme.dimens.radius,
            horizontalPadding = BaseTheme.dimens.dp4,
            verticalPadding = BaseTheme.dimens.dp06
        ) {}
    }
}