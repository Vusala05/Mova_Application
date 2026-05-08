package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.domain.models.MovieUiModel

@Composable
fun MovieGrid(
    movies: List<MovieUiModel>,
    onMovieClick: (Int,String) -> Unit,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    verticalSpacing: Dp = BaseTheme.dimens.dp4,
    horizontalSpacing: Dp = BaseTheme.dimens.dp4,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        contentPadding = contentPadding
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieCoverItem(
                movieModel = movie,
                onClickMovie = { onMovieClick(movie.id,movie.type) }
            )
        }
    }
}