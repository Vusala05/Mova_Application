package com.example.crocusoft_mova.presentation.dashboard.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.domain.models.MovieUiModel

@Composable
 fun HeaderItem (
    movie: MovieUiModel,
    onClick : (Int) -> Unit){
    Box(modifier = Modifier.fillMaxSize()
        .clickable(onClick = {onClick(movie.id)})) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ApiConstants.getPosterUrl(movie.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,

        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(Colors.transparent),
                            colorResource(Colors.primary).copy(alpha = 0.9f)
                        )
                    )
                ),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BaseTheme.dimens.dp6)
                .padding(bottom = 80.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(text = movie.title, style = BaseTextStyle.t24Bold)
            Text(
                text = movie.description,
                style = BaseTextStyle.t12,
                maxLines = 2
            )
        }
    }
}