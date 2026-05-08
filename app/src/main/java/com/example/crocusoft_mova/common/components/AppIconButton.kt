package com.example.crocusoft_mova.common.components

import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings


@Composable
fun AppIconButton(
    icon: Int,
    modifier: Modifier = Modifier,
    borderColor: Int = Colors.dark2,
    color: Int = Colors.primary,
    textRes: Int? = null,
    radius: Dp = BaseTheme.dimens.dp4,
    horizontalPadding: Dp = BaseTheme.dimens.dp8,
    verticalPadding: Dp = BaseTheme.dimens.dp4Half,
    iconSize: Dp = BaseTheme.dimens.dp6,
    textColor: Int = Colors.white,
    onClick: () -> Unit,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .border(
                border = BorderStroke(
                    width = BaseTheme.dimens.dp01,
                    color = colorResource(borderColor)
                ),
                shape = RoundedCornerShape(radius)
            )
            .background(color = colorResource(color))
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .clickable(
                onClick = onClick

            )
    ) {

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2)
        ) {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(icon),
                contentDescription = null
            )

            if (textRes != null) {
                Text(
                    text = stringResource(textRes),
                    style = BaseTextStyle.t14SemiBold.copy(color = colorResource(textColor))
                )
            }
        }

    }
}


@Composable
@Preview
fun AppIconButtonPreview() {
    AppIconButton(
        modifier = Modifier.fillMaxWidth(),
        icon = Drawables.facebook,
        textRes = Strings.continue_apple
    ) { }
}