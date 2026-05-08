package com.example.crocusoft_mova.core

import com.example.crocusoft_mova.core.constants.Dimens

abstract class BaseTheme {


    companion object {
        val dimens: Dimens
            get() = Dimens

        val textStyle: BaseTextStyle
            get() = BaseTextStyle
    }
}