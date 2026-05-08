package com.example.crocusoft_mova.presentation.dashboard.my_list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.MovieCoverItem
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.common.components.TagChip
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.data.mappers.toMovieUiModel
import kotlinx.coroutines.flow.SharedFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyListContent(
    state: MyListContract.State,
    postIntent: (MyListContract.Intent) -> Unit,
    effect : SharedFlow<MyListContract.Effect>,
    onNavigateToDetail : (Int) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(effect) {
        effect.collect {
            when(it){
               is MyListContract.Effect.ShowError ->{
                   Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
               }
                is MyListContract.Effect.NavigateToDetail ->{
                     onNavigateToDetail(it.movieId)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(BaseTheme.dimens.dp6),
        topBar = {
            AppTopBar(
                modifier = Modifier
                    .padding(top = BaseTheme.dimens.dp2)
                    .statusBarsPadding(),
                prefixIcon = Drawables.logo,
                prefixColor = Colors.secondary,
                title = stringResource(Strings.my_list),
                prefixAction = {},
                isSuffixIconVisible = true,
                suffixIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(Drawables.icon_search),
                            contentDescription = null,
                            tint = colorResource(Colors.white)
                        )
                    }
                }

            )
        },
        containerColor = colorResource(Colors.primary)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = BaseTheme.dimens.dp6)
        ) {

            when {

                state.isLoading -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = colorResource(Colors.secondary)
                        )
                    }
                }

                state.movies.isEmpty() -> {

                    NotAnyComponent(
                        imageRes = Drawables.empty_list,
                        title = stringResource(Strings.empty_list),
                        description = stringResource(Strings.empty_list_description)
                    )
                }

                else -> {

                    LazyRow(
                        modifier = Modifier.padding(bottom = BaseTheme.dimens.dp6),
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
                    ) {

                        items(state.categories) { category ->

                            TagChip(
                                text = category,
                                isSelected = state.selectedCategory == category,
                                onChipAction = {
                                    postIntent(
                                        MyListContract.Intent.OnSelectCategory(category)
                                    )
                                }
                            )
                        }
                    }

                    LazyVerticalGrid(
                        modifier = Modifier.weight(1f),
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                        verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
                    ) {

                        items(state.filteredMovies) { movie ->

                            MovieCoverItem(
                                movieModel = movie.toMovieUiModel(),
                                onClickMovie = {
                                    postIntent(
                                        MyListContract.Intent.OnMovieClick(movie.id)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }



    }
