package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.MovieGrid
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract
import com.example.crocusoft_mova.presentation.dashboard.explore.isAnyFilteredApplied

@Composable
fun GridResultsSection(
    state: ExploreContract.State,
    onNavigateDetail: (Int, String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (state.isAnyFilteredApplied()) {
            FilteredResultsList(state)
        } else {
            Text(
                text = stringResource(Strings.top_searches),
                style = BaseTheme.textStyle.t16Bold,
                modifier = Modifier.padding(start = BaseTheme.dimens.dp6)
            )
        }

        VerticalSpacer(BaseTheme.dimens.dp6)

        MovieGrid(
            movies = state.movies,
            onMovieClick = onNavigateDetail,
            modifier = Modifier.padding(horizontal = BaseTheme.dimens.dp6),
            contentPadding = PaddingValues(bottom = BaseTheme.dimens.dp20)
        )
    }
}