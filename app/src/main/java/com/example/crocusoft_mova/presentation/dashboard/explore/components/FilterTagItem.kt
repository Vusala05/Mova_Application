package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors

@Composable
fun FilterTagItem(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BaseTheme.dimens.radius))
            .background(color = colorResource(Colors.secondary))
            .height(BaseTheme.dimens.tag_item_height)
            .padding(horizontal = BaseTheme.dimens.dp10),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BaseTheme.textStyle.t16SemiBoldWhite,
            maxLines = 1
        )
    }
}