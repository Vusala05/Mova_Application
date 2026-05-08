package com.example.crocusoft_mova.presentation.dashboard.movie_detail


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.constants.ApiConstants
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.components.DetailTabRow
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.components.MovieDetailHeader
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.components.ProductionCompanyItem
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.components.TrailerItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailContent(
    state: MovieDetailContract.State,
    postIntent: (MovieDetailContract.Intent) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateDetail: (Int) -> Unit
) {
    Scaffold(
        containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(
                modifier = Modifier
                    .padding(horizontal = BaseTheme.dimens.dp3)
                    .statusBarsPadding(),
                prefixAction = onNavigateBack,
                suffixIcon = {
                    Icon(
                        painter = painterResource(Drawables.icon_cast),
                        contentDescription = null,
                        tint = colorResource(Colors.white)
                    )
                },
                isSuffixIconVisible = true
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp4)
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(BaseTheme.dimens.detail_header_height)
                ) {
                    AsyncImage(
                        model = ApiConstants.getPosterUrl(state.movieDetail.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            item {
                MovieDetailHeader(state = state, postIntent = postIntent)
            }

            if (state.movieDetail.production_companies.isNotEmpty()) {
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp5),
                        contentPadding = PaddingValues(horizontal = BaseTheme.dimens.dp4)
                    ) {
                        items(state.movieDetail.production_companies) { company ->
                            ProductionCompanyItem(
                                model = ApiConstants.getPosterUrl(company.logo_path),
                                title = company.name,
                                subTitle = "Production"
                            )
                        }
                    }
                }
            }

            stickyHeader {
                DetailTabRow(
                    selectedTab = state.selectedTab,
                    onTabSelected = { index ->
                        postIntent(MovieDetailContract.Intent.OnTabSelected(index))
                    }
                )
            }

            when (state.selectedTab) {

                0 -> {
                    if (state.trailers.isEmpty()) {
                        item {
                            Text(
                                text = "No trailers",
                                modifier = Modifier.padding(BaseTheme.dimens.dp4),
                                color = colorResource(Colors.dark_gray)
                            )
                        }
                    } else {
                        items(state.trailers) { trailer ->
                            TrailerItem(
                                model = "https://img.youtube.com/vi/${trailer.key}/hqdefault.jpg",
                                title = trailer.title,
                                duration = trailer.duration
                            )
                        }
                    }
                }

                1 -> {
                    val rows = state.similarMovies.chunked(2)

                    items(rows) { rowItems ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = BaseTheme.dimens.dp6),
                            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp4)
                        ) {
                            rowItems.forEach { movie ->
                                Box(modifier = Modifier.weight(1f)) {
                                    MovieCoverItem(
                                        movieModel = movie,
                                        onClickMovie = {
                                            onNavigateDetail(movie.id)
                                        }
                                    )
                                }
                            }

                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }

                2 -> {
                    item {
                        Text(
                            text = "Comments coming soon...",
                            modifier = Modifier.padding(BaseTheme.dimens.dp6),
                            style = BaseTheme.textStyle.t14.copy(color = Color.Gray)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(BaseTheme.dimens.dp10))
            }
        }
    }
}