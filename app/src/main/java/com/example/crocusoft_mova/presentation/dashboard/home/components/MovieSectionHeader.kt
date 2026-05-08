package com.example.crocusoft_mova.presentation.dashboard.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.crocusoft_mova.core.BaseTheme

@Composable
fun SectionHeader(
    title: String,
    seeAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(BaseTheme.dimens.dp6),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = BaseTheme.textStyle.t20Bold)
        TextButton(onClick = seeAllClick) {
            Text(text = "See all", style = BaseTheme.textStyle.t14)
        }
    }
}
