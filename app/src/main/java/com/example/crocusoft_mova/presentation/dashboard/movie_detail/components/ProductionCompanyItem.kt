package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun ProductionCompanyItem(
    model: String,
    title: String,
    subTitle: String = "Producer"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
    ) {
        AsyncImage(
            model = model,
            contentDescription = null,
            modifier = Modifier
                .size(BaseTheme.dimens.dp10)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = BaseTheme.textStyle.t10,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subTitle,
                style = BaseTheme.textStyle.t10.copy(
                    color = colorResource(Colors.white).copy(alpha = 0.7f)
                )
            )
        }
    }
}