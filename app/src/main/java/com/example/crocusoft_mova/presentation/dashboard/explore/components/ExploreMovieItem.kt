package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.domain.models.MovieUiModel


@Composable
fun ExploreMovieItem(
    movieUiModel: MovieUiModel,
    onClick: (Int, String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp5),
        modifier = Modifier
            .fillMaxWidth()
            .height(BaseTheme.dimens.searched_item_height)
            .clickable(
                onClick = { onClick(movieUiModel.id,movieUiModel.type) }
            )
    ) {

        AsyncImage(
            model = ApiConstants.getPosterUrl(movieUiModel.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(BaseTheme.dimens.video_height)
                .width(BaseTheme.dimens.video_width)
                .clip(RoundedCornerShape(BaseTheme.dimens.dp010))

        )

        Text(
            textAlign = TextAlign.Center,
            text = movieUiModel.title,
            style = BaseTheme.textStyle.t14SemiBold
        )
    }
}