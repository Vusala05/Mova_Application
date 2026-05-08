package com.example.crocusoft_mova.common.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.domain.models.MovieUiModel


@Composable
fun MovieCoverItem(
    movieModel: MovieUiModel,
    onClickMovie: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .width(width = BaseTheme.dimens.movie_cover_width)
            .height(BaseTheme.dimens.movie_cover_height)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = {
                    onClickMovie(movieModel.id)
                }
            )
    ) {
        AsyncImage(
            model = ApiConstants.getPosterUrl(movieModel.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(BaseTheme.dimens.dp3),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(BaseTheme.dimens.rate_box_width)
                    .height(BaseTheme.dimens.rate_box_height)
                    .clip(RoundedCornerShape(BaseTheme.dimens.dp06))
                    .background(colorResource(Colors.secondary))
            )

            Text(
                text = "${movieModel.vote_average}", style = BaseTheme.textStyle.t10,
                color = colorResource(Colors.white),
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
fun AppCoverItemPreview() {

}