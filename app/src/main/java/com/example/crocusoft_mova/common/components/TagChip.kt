package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun TagChip(
    text: String,
    onChipAction: () -> Unit,
    isSelected: Boolean = false
) {

    Button(
        shape = RoundedCornerShape(BaseTheme.dimens.radius),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) colorResource(Colors.secondary) else Color.Transparent
        ),
        border = BorderStroke(
            width = BaseTheme.dimens.dp01,
            color = colorResource(Colors.secondary)
        ),
        onClick = { onChipAction() }
    ) {
        Text(
            text = text,
            style = if(isSelected) BaseTheme.textStyle.t16Bold else BaseTheme.textStyle.t16BoldRed
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TagChipPreview(){

    TagChip(
        text = "Action",
        onChipAction = {},
        isSelected = false
    )
}