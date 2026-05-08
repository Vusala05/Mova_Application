package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors

@Composable
fun MovieInfoBadge(
    text: String
) {
    Box(
        modifier = Modifier
            .height(BaseTheme.dimens.dp6)
            .clip(
                RoundedCornerShape(BaseTheme.dimens.dp06)
            )
            .border(
                width = BaseTheme.dimens.dp01,
                color = colorResource(Colors.secondary),
                shape = RoundedCornerShape(BaseTheme.dimens.dp06)
            )
            .background(color = Color.Transparent,
                shape = RoundedCornerShape(BaseTheme.dimens.dp06))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(6.dp),
            style = BaseTheme.textStyle.t10.copy(color = colorResource(Colors.secondary))
        )
    }
}