package com.example.crocusoft_mova.presentation.language.components

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.Colors

@Composable
fun CustomRadioButton(
    isSelected : Boolean = false
){
    RadioButton(
        selected = isSelected,
        onClick = null,
        colors = RadioButtonDefaults.colors(
            selectedColor = colorResource(Colors.secondary),
            unselectedColor = colorResource(Colors.secondary)
        )
    )
}