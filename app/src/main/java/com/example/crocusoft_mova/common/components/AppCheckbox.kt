package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(
                RoundedCornerShape(BaseTheme.dimens.dp2)
            )
            .border(
                border = BorderStroke(
                    width = BaseTheme.dimens.dp03,
                    color = colorResource(Colors.secondary),
                ), shape = RoundedCornerShape(BaseTheme.dimens.dp2)
            )
            .background(
                color = if (checked) colorResource(Colors.secondary) else Color.Transparent,
            )

            .clickable(
                onClick = { onCheckedChange(!checked) }
            )
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
                tint = colorResource(Colors.white)
            )
        }
    }

}


@Preview
@Composable
fun AppCheckboxPreview() {
    AppCheckbox(
        checked = false,
        onCheckedChange = {}
    )
}