package com.example.crocusoft_mova.presentation.auth.splash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.Colors

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .size(width = if (index == currentPage) 32.dp else 8.dp,
                        height = 8.dp )
                    .background(
                        if (index == currentPage) Color.Red else colorResource(Colors.white),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
@Preview
fun AppIconButtonPrevieww(){
    PagerIndicator(3,0)
}