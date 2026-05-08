package com.example.crocusoft_mova.core

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.crocusoft_mova.ui.theme.Red
import com.example.crocusoft_mova.ui.theme.White

object BaseTextStyle {

    val urbanistFont = FontFamily(
        Font(Fonts.urbanist_regular, FontWeight.Normal),
        Font(Fonts.urbanist_medium, FontWeight.Medium),
        Font(Fonts.urbanist_semibold, FontWeight.SemiBold),
        Font(Fonts.urbanist_bold, FontWeight.Bold)

    )

    val t10Bold = TextStyle(
        fontFamily = urbanistFont,
        fontSize = BaseTheme.dimens.spMin,
        fontWeight = FontWeight.W700,

        )
    val t18Bold = TextStyle(
        fontSize = BaseTheme.dimens.sp3,
        fontWeight = FontWeight.W700,
        color = White
        )

    val t10 = TextStyle(
        fontSize = BaseTheme.dimens.spMin,
        fontWeight = FontWeight.W600,
        color = White
    )

    val t12 = TextStyle(
        fontFamily = urbanistFont,
        fontSize = BaseTheme.dimens.sp0,
        fontWeight = FontWeight.W500,
        color = White
    )

    val t14 = TextStyle(
        fontSize = BaseTheme.dimens.sp1,
        fontWeight = FontWeight.W600,
        color = Red
    )


    val t20Bold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp4,
        fontWeight = FontWeight.W700
    )

    val t36Bold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp12,
        fontWeight = FontWeight.W700,
    )

    val t48Bold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp18,
        fontWeight = FontWeight.W700,
    )

    val t16Bold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W700
    )

    val t16BoldRed = TextStyle(
        fontFamily = urbanistFont,
        color = Red,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W700
    )

    val t16SemiBoldRed = TextStyle(
        fontFamily = urbanistFont,
        color = Red,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W500
    )
    val t16SemiBoldWhite = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp2,
        fontWeight = FontWeight.W600
    )

    val t18SemiBold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp3,
        fontWeight = FontWeight.W600
    )

    val t14SemiBold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp1,
        fontWeight = FontWeight.W600
    )


    val t24Bold = TextStyle(
        fontFamily = urbanistFont,
        color = White,
        fontSize = BaseTheme.dimens.sp6,
        fontWeight = FontWeight.W700
    )
}