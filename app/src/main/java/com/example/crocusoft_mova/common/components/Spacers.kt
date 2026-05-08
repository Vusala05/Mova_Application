package com.example.crocusoft_mova.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun DSpacer(all: Dp){

    Spacer(modifier = Modifier.size(all))
}

@Composable
fun DSpacer(width:Dp,height: Dp){
    Spacer(modifier = Modifier.size(height = height, width = width))
}

@Composable
fun HorizontalSpacer(width: Dp){
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun VerticalSpacer(height: Dp){
    Spacer(modifier = Modifier.height(height))
}