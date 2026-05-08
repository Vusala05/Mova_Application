package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors

@Composable
fun ClickableText(
       preText:String,
       sufText:String,
       onClick: ()->Unit,
){
    Row {
        Text(

            text = preText,
            style = BaseTheme.textStyle.t14SemiBold
        )

        Text(
            modifier = Modifier.clickable(
                onClick = onClick
            ),
            text = sufText,
            color = colorResource(Colors.secondary),
            style = BaseTheme.textStyle.t14SemiBold
        )
    }
}