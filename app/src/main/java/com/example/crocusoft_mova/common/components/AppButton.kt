package com.example.crocusoft_mova.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors


@Composable
fun AppButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    action: () -> Unit,
    isLoading: Boolean = false,
    text: String,
    color: Color = colorResource(Colors.secondary)
) {

    Button(
        modifier = modifier,
        shape = RoundedCornerShape(BaseTheme.dimens.dp4),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        onClick = action
    ) {
        AnimatedVisibility(
            visible = isLoading,
            enter = slideInHorizontally(
                initialOffsetX = { -it * 3 }
            ) + fadeIn(),
            exit = slideOutHorizontally(
                targetOffsetX = { it * 2 }
            ) + fadeOut()
        ) {
            CircularProgressIndicator()

        }

        if (!isLoading)

            Text(
                text = text,
                style = BaseTheme.textStyle.t16Bold
            )


    }

}


@Preview
@Composable
fun AppButtonPreview() {

    AppButton(
        action = {},
        text = "Submit"
    )
}