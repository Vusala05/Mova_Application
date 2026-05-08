package com.example.crocusoft_mova.presentation.auth.pin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.core.BaseTheme

@Composable
fun OtpInput(
    length: Int = 4,
    otpValues: List<String>,
    onValueChange: (Int, String) -> Unit
) {
    val focusRequesters = List(length) { FocusRequester() }

    Row(
        horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
    ) {
        repeat(length) { index ->

            AppTextField(
                isPasswordField = true,
                requiredSuffixIcon = false,
                keyboardType = KeyboardType.NumberPassword,
                value = otpValues[index],
                onValueChange = { value ->

                    if (value.length <= 1 && value.all { it.isDigit() }) {

                        onValueChange(index, value)

                        if (value.isNotEmpty() && index < length - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                placeholder = "",
                isPin = true,
                modifier = Modifier
                    .width(BaseTheme.dimens.dp16)
                    .focusRequester(focusRequesters[index])
            )
        }
    }
}