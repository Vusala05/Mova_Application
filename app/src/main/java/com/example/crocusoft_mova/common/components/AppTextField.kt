package com.example.crocusoft_mova.common.components

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables


@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    prefixIcon: Int? = null,
    suffixIcon: Int = Drawables.hide,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordField: Boolean = false,
    requiredSuffixIcon: Boolean = false,
    placeholder: String = "",
    isPin: Boolean = false,
) {

    val isFocused = remember { mutableStateOf(false) }

    val showPassword = remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        modifier = modifier.onFocusChanged {
            isFocused.value = it.isFocused
        },
        onValueChange = onValueChange,
        textStyle = BaseTheme.textStyle.t14SemiBold,

        visualTransformation =
            if (isPasswordField && !showPassword.value)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (!isPasswordField) {
                keyboardType
            } else {
                if (showPassword.value) KeyboardType.Text else KeyboardType.Password
            }
        ),
        decorationBox = { innerTextField ->

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(BaseTheme.dimens.dp3))
                    .height(BaseTheme.dimens.dp15)

                    .background(
                        color = if (!isFocused.value) colorResource(Colors.dark) else colorResource(
                            Colors.secondary
                        ).copy(alpha = 0.1f),
                    )
                    .border(
                        border = BorderStroke(
                            width = BaseTheme.dimens.dp02,
                            color = if (isFocused.value) colorResource(
                                Colors.secondary
                            ) else Color.Transparent
                        ),
                        shape = RoundedCornerShape(BaseTheme.dimens.dp3)
                    )
                    .fillMaxWidth()


                    .padding(horizontal = BaseTheme.dimens.dp2, vertical = BaseTheme.dimens.dp1)
            ) {

                if (prefixIcon != null) {
                    Icon(
                        painter = painterResource(prefixIcon),
                        contentDescription = null,
                        tint = if (isFocused.value) colorResource(Colors.secondary) else colorResource(
                            Colors.gray
                        )
                    )
                    Spacer(modifier = Modifier.width(BaseTheme.dimens.dp3))
                }

                Box(modifier = Modifier.weight(1f)) {

                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = colorResource(Colors.gray)
                        )
                    }

                    innerTextField()
                }

                if ((isPasswordField || requiredSuffixIcon) && !isPin) {
                    Spacer(modifier = Modifier.width(BaseTheme.dimens.dp3))

                    IconButton(
                        onClick = { if (isPasswordField) showPassword.value = !showPassword.value }
                    ) {
                        Icon(
                            painter = painterResource(suffixIcon),
                            contentDescription = null,
                            tint = if (isFocused.value) colorResource(Colors.secondary) else colorResource(
                                Colors.gray
                            )
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun AppTextFieldPreview() {
    AppTextField(
        value = "",
        onValueChange = {},
        placeholder = "Write something...",
        prefixIcon = null,
        isPasswordField = true
    )
}