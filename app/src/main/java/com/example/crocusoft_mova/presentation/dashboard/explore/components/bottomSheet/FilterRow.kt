package com.example.crocusoft_mova.presentation.dashboard.explore.components.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.common.components.TagChip
import com.example.crocusoft_mova.core.BaseTheme

@Composable
fun FilterRow(
    title: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp6)
    ) {

        Text(
            text = title,
            style = BaseTheme.textStyle.t20Bold,
        )


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3),
        ) {
            items(items) { item ->
                TagChip(
                    text = item,
                    isSelected = item == selectedItem,
                    onChipAction = { onItemSelected(item) }
                )
            }
        }
    }
}