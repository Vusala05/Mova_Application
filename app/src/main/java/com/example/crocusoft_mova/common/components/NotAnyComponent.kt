package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.constants.Dimens


@Composable
fun NotAnyComponent(
    imageRes: Int,
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(Colors.primary))
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.size(BaseTheme.dimens.image_height),
                painter = painterResource(imageRes),
                contentDescription = title,
            )

            Text(
                text = title,
                style = BaseTextStyle.t18SemiBold.copy(color = colorResource(Colors.secondary)),
            )

            Text(
                textAlign = TextAlign.Center,
                text = description,
                style = BaseTextStyle.t14SemiBold
            )
        }


    }
}