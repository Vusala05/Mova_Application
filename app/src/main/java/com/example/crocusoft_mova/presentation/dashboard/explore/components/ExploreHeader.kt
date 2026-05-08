package com.example.crocusoft_mova.presentation.dashboard.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings

@Composable
fun ExploreHeader(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    onFilterClick : () -> Unit
    ){
    Row(modifier = modifier.fillMaxWidth().padding(horizontal = BaseTheme.dimens.dp6).padding(vertical = BaseTheme.dimens.dp6),
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3),
        verticalAlignment = Alignment.CenterVertically) {

        AppTextField(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            prefixIcon = Drawables.icon_search,
            placeholder = stringResource(Strings.search)
        )
        IconButton(onClick = onFilterClick) {
            Icon(
                painter = painterResource(Drawables.filter),
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = colorResource(Colors.secondary)
            )
        }

    }

}