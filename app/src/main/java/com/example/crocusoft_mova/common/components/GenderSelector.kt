package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Genders

@Composable
fun GenderSelector(
    value: String,
    onGenderSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppTextField(
            value = value,
            onValueChange = {},
            placeholder = "Gender",
            requiredSuffixIcon = true,
            suffixIcon = Drawables.icon_down,
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            containerColor = colorResource(Colors.dark),
            shape = RoundedCornerShape(BaseTheme.dimens.dp4),
            onDismissRequest = { expanded = false }
        ) {
            Genders.entries.forEach { gender ->
                DropdownMenuItem(
                    text = { Text(gender.name, color = colorResource(Colors.gray)) },
                    onClick = {
                        onGenderSelected(gender.name)
                        expanded = false
                    }
                )
            }
        }
    }
}