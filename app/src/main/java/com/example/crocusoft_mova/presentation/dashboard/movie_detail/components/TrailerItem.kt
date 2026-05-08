package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables

@Composable
fun TrailerItem(
    modifier: Modifier = Modifier,
    model: String,
    title: String,
    duration: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
           .padding(horizontal = BaseTheme.dimens.dp6),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp5)
    ) {
        Box(
            modifier = Modifier
                .size(width = BaseTheme.dimens.video_width, height = BaseTheme.dimens.video_height)
                .clip(RoundedCornerShape(BaseTheme.dimens.dp010)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = model,
                contentDescription = null,

                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Icon(
                painter = painterResource(id = Drawables.icon_play),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(BaseTheme.dimens.dp6)
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)) {
            Text(text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = BaseTheme.textStyle.t18Bold,
                color = colorResource(Colors.white))
            Text(
                text = duration,
                style = BaseTheme.textStyle.t14SemiBold
            )
        }
    }
}