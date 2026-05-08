package com.example.crocusoft_mova.presentation.auth.splash.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crocusoft_mova.core.Colors

@Composable
fun OnboardingText(
    title: String,
    description: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = colorResource(Colors.white),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = description,
            color = colorResource(Colors.white),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}