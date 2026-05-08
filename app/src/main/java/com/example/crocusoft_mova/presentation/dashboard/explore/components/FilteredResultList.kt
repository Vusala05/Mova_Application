package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract
import com.example.crocusoft_mova.presentation.dashboard.explore.selectedFilterTags

@Composable
fun FilteredResultsList(state: ExploreContract.State) {
    val tags = state.selectedFilterTags()
    if (tags.isNotEmpty()) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp4),
            contentPadding = PaddingValues(horizontal = BaseTheme.dimens.dp6)
        ) {
            items(tags) { tag ->
                FilterTagItem(text = tag)
            }
        }
    }
}

