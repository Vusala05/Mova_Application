package com.example.crocusoft_mova.presentation.dashboard.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.domain.models.MovieUiModel

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator

@Composable
fun HomeHeader(
    movies: List<MovieUiModel>,
    isLoading: Boolean,
    onClick : (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BaseTheme.dimens.home_header_cover_height)
    ) {

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            movies.isNotEmpty() -> {
                val pagerState = rememberPagerState(pageCount = { movies.size })

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    val movie = movies[page]
                    HeaderItem(movie,onClick)
                }

                HeaderButtons(modifier = Modifier.align(Alignment.BottomStart))
            }
        }
    }
}
