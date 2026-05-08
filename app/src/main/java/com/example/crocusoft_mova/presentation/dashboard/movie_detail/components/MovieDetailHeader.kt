package com.example.crocusoft_mova.presentation.dashboard.movie_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.crocusoft_mova.common.components.AppIconButton
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.MovieDetailContract


@SuppressLint("DefaultLocale")
@Composable
fun MovieDetailHeader(
    state: MovieDetailContract.State,
    postIntent : (MovieDetailContract.Intent) -> Unit
) {
    val movie = state.movieDetail

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BaseTheme.dimens.dp6),
        verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp5)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = movie.title,
                style = BaseTheme.textStyle.t24Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)) {
                IconButton(onClick = {
                    postIntent(MovieDetailContract.Intent.OnBookMarkClick)
                }) {
                    Icon(
                        painter = painterResource(if(state.isBookMarked)Drawables.bookmark_filled
                        else Drawables.bookmark),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(Drawables.icon_send),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
        ) {
            Icon(
                painter = painterResource(Drawables.icon_star),
                contentDescription = null,
                tint = colorResource(Colors.secondary),
                modifier = Modifier.size(BaseTheme.dimens.dp4)
            )
            Text(
                text = String.format("%.1f", movie.vote_average),
                style = BaseTheme.textStyle.t14.copy(color = colorResource(Colors.secondary))
            )
            Icon(
                painter = painterResource(Drawables.icon_right),
                contentDescription = null,
                tint = colorResource(Colors.secondary),
                modifier = Modifier.size(BaseTheme.dimens.dp3)
            )

            Text(text = movie.release_date.take(4), style = BaseTheme.textStyle.t14)

            MovieInfoBadge(text = "13+")
            MovieInfoBadge(text = movie.language.uppercase())
            MovieInfoBadge(text = "Subtitle")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
        ) {
            AppIconButton(
                modifier = Modifier.weight(1f),
                textRes = Strings.play,
                icon = Drawables.icon_play,
                color = Colors.secondary,
                onClick = {  }
            )
            AppIconButton(
                modifier = Modifier.weight(1f),
                textRes = Strings.download,
                icon = Drawables.download,
                color = Colors.transparent,
                borderColor = Colors.secondary,
                textColor = Colors.secondary,
                onClick = {  }
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)) {
            Text(
                text = "Genre: ${movie.genres.joinToString(" ") }",
                style = BaseTheme.textStyle.t12,
                color = Color.White.copy(alpha = 0.9f)
            )


            Text(
                text = movie.description,
                style = BaseTheme.textStyle.t14,
                lineHeight = 20.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}