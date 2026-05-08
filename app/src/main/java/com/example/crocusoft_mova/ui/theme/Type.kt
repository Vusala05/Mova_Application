package com.example.crocusoft_mova.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.crocusoft_mova.core.Fonts

// Set of Material typography styles to start with

val urbanistFont = FontFamily(
    Font(Fonts.urbanist_regular, FontWeight.Normal),
    Font(Fonts.urbanist_medium, FontWeight.Medium),
    Font(Fonts.urbanist_semibold, FontWeight.SemiBold),
    Font(Fonts.urbanist_bold, FontWeight.Bold)

)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)