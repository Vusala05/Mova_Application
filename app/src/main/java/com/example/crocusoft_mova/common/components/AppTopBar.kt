package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables


@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    prefixAction: () -> Unit,
    prefixIcon: Int = Drawables.back,
    prefixColor: Int = Colors.white,
    suffixIcon: @Composable () -> Unit = {},
    isSuffixIconVisible: Boolean = false,
    title: String? = null,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(height = BaseTheme.dimens.dp7)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp4)
        ) {
            IconButton(
                onClick = prefixAction
            ) {
                Icon(
                    painter = painterResource(prefixIcon),
                    contentDescription = null,
                    tint = colorResource(prefixColor)
                )
            }

            if (title != null) {
                Text(
                    text = title,
                    style = BaseTextStyle.t18SemiBold
                )
            }

        }
        if (isSuffixIconVisible) {
            suffixIcon()
        }

    }
}

@Composable
@Preview
fun AppTopBarPreview() {
    AppTopBar(
        prefixAction = {},
        suffixIcon = {}
    )
}

