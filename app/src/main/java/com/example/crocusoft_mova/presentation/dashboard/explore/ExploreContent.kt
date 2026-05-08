package com.example.crocusoft_mova.presentation.dashboard.explore

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.explore.components.ExploreHeader
import com.example.crocusoft_mova.presentation.dashboard.explore.components.ExploreMovieItem
import com.example.crocusoft_mova.presentation.dashboard.explore.components.bottomSheet.FilterDesignContent
import com.example.crocusoft_mova.presentation.dashboard.explore.components.GridResultsSection
import kotlinx.coroutines.flow.SharedFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreContent(
    state: ExploreContract.State,
    postIntent: (ExploreContract.Intent) -> Unit,
    onNavigateDetail: (Int, String) -> Unit,
    effect : SharedFlow<ExploreContract.Effect>
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(effect) {
        effect.collect {
            when(it){
                is ExploreContract.Effect.ShowError ->{
                    Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
             .padding(top = BaseTheme.dimens.dp6)

    ) {
        ExploreHeader(
            value = state.searchQuery,
            onValueChange = {
                postIntent(ExploreContract.Intent.SetQuery(it))
            },
            onFilterClick = { postIntent(ExploreContract.Intent.ToggleFilterSheet(true))}
        )


        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator(color = colorResource(Colors.secondary))
                }
            }

            state.movies.isEmpty() && (state.searchQuery.isNotEmpty() || state.isAnyFilteredApplied()) -> {
                NotAnyComponent(
                    imageRes = Drawables.not_found,
                    title = stringResource(Strings.not_found),
                    description = stringResource(Strings.not_found_description)
                )
            }


            state.searchQuery.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = BaseTheme.dimens.dp6),
                    verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3),
                    contentPadding = PaddingValues(bottom = BaseTheme.dimens.dp15)
                ) {

                    items(
                        count = state.movies.size,
                        key = { state.movies[it].id }
                    ) {
                        ExploreMovieItem(
                            movieUiModel = state.movies[it],
                            onClick = onNavigateDetail
                        )


                    }
                }
            }
            else-> GridResultsSection(state,onNavigateDetail)


        }
        if (state.isBottomSheetVisible) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    postIntent(ExploreContract.Intent.ToggleFilterSheet(false))
                },
                containerColor = colorResource(Colors.dark),
                shape = RoundedCornerShape(topStart = BaseTheme.dimens.dp10 , topEnd = BaseTheme.dimens.dp10)
            ) {
                FilterDesignContent(state,postIntent)
            }
        }
    }
}
