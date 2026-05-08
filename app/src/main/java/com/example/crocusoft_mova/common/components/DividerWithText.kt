package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun DividerWithText(
    text: String
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
        )

        Text(
            text = text,
            style = BaseTheme.textStyle.t18SemiBold
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = colorResource(Colors.gray).copy(alpha = 0.4f)
        )
    }
}