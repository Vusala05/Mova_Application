package com.example.crocusoft_mova.presentation.dashboard.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.core.*
import com.example.crocusoft_mova.presentation.dashboard.home.components.HomeHeader
import com.example.crocusoft_mova.presentation.dashboard.home.components.SectionHeader
import kotlinx.coroutines.flow.SharedFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    state: HomeContract.State,
    effect: SharedFlow<HomeContract.Effect>,
    onNavigateDetail: (Int) -> Unit,
    onNavigateMovieList : (MovieCategoryType) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is HomeContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(Colors.primary),
        snackbarHost = { SnackbarHost(snackbarHostState) },

        topBar = {
            AppTopBar(
                modifier = Modifier.padding(BaseTheme.dimens.dp6),
                prefixIcon = Drawables.logo,
                prefixAction = {},
                prefixColor = Colors.secondary,
                suffixIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(Drawables.icon_search),
                                contentDescription = null,
                                tint = colorResource(Colors.white)
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(Drawables.icon_notification),
                                contentDescription = null,
                                tint = colorResource(Colors.white)
                            )
                        }
                    }
                },
                isSuffixIconVisible = true
            )
        }
    ) {

        LazyColumn {

            item {
                HomeHeader(
                    movies = state.nowPlayingMovies,
                    isLoading = state.isNowPlayingLoading,
                    onClick = {id->
                        onNavigateDetail(id)
                    }
                )
            }

            item {
                SectionHeader(
                    title = stringResource(Strings.top_movies),
                    seeAllClick = {
                     onNavigateMovieList(MovieCategoryType.TOP_RATED)
                    }
                )
            }

            item {
                if (state.isTopRatedLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = BaseTheme.dimens.dp6),
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                    ) {
                        items(state.topRatedMovies.take(20)) { movie ->
                            MovieCoverItem(
                                movieModel = movie,
                                onClickMovie = {
                                    onNavigateDetail(movie.id)
                                }
                            )
                        }
                    }
                }
            }

            item {
                SectionHeader(
                    title = stringResource(Strings.new_releases),
                    seeAllClick = {
                        onNavigateMovieList(MovieCategoryType.NEW_RELEASES)
                    }
                )
            }

            item {
                if (state.isUpcomingLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = BaseTheme.dimens.dp6),
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                    ) {
                        items(state.upcomingMovies.take(20)) { movie ->
                            MovieCoverItem(
                                movieModel = movie,
                                onClickMovie = {
                                    onNavigateDetail(movie.id)
                                }
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(BaseTheme.dimens.bottom_padding))
            }
        }
    }
}