package com.example.crocusoft_mova.presentation.dashboard.explore.components.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract
import com.example.crocusoft_mova.presentation.dashboard.explore.util.CategoryType
import com.example.crocusoft_mova.presentation.dashboard.explore.util.ExplorePeriod
import com.example.crocusoft_mova.presentation.dashboard.explore.util.SortOption

@Composable
fun FilterDesignContent(
  state : ExploreContract.State,
  onIntent : (ExploreContract.Intent) -> Unit
) {
    val categories = CategoryType.entries.map {it.displayName}
    val periods = ExplorePeriod.getPeriodOption()
    val sortOptions = SortOption.entries.map { it.displayName }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BaseTheme.dimens.dp6),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(Strings.sort_filter),
            style = BaseTheme.textStyle.t24Bold.copy(color = colorResource(Colors.light_red)),
        )

        VerticalSpacer(BaseTheme.dimens.dp5)

        HorizontalDivider(
            thickness = 1.dp,
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Categories",
            items = categories,
            selectedItem = state.selectedCategory,
            onItemSelected = { onIntent(ExploreContract.Intent.SelectCategory(it)) }
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Regions",
            items = state.regions.map { it.englishName },
            selectedItem = state.selectedRegion,
            onItemSelected = { onIntent(ExploreContract.Intent.SelectRegion(it)) }
        )

        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Genre",
            items = state.currentGenresToDisplay.map { it.name },
            selectedItem = state.selectedGenre,
            onItemSelected = { onIntent(ExploreContract.Intent.SelectGenre(it)) }
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Time/Periods",
            items = periods,
            selectedItem = state.selectedPeriod,
            onItemSelected = { onIntent(ExploreContract.Intent.SelectPeriod(it)) }
        )
        VerticalSpacer(BaseTheme.dimens.dp5)

        FilterRow(
            title = "Sorts",
            items = sortOptions,
            selectedItem = state.selectedSort,
            onItemSelected = { onIntent(ExploreContract.Intent.SelectSort(it)) }
        )


        HorizontalDivider(
            modifier = Modifier.padding(vertical = BaseTheme.dimens.dp6),
            thickness = 1.dp,
        )
    }

        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = BaseTheme.dimens.dp6),
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp6)
        ) {
            AppButton(
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                text = stringResource(Strings.reset),
                action = {onIntent(ExploreContract.Intent.ResetFilters)}
            )
            AppButton(
                modifier = Modifier.weight(1f),
                text = stringResource(Strings.apply),
                action = {onIntent(ExploreContract.Intent.ApplyFilters) }
            )
        }

    }


