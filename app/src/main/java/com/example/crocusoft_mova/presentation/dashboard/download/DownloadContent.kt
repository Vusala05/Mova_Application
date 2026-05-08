package com.example.crocusoft_mova.presentation.dashboard.download

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.NotAnyComponent
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DownloadContent(
    state: DownloadContract.State,
    postIntent: (DownloadContract.Intent) -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            AppTopBar(
                modifier = Modifier.padding(top = BaseTheme.dimens.dp2),
                prefixIcon = Drawables.logo,
                prefixColor = Colors.secondary,
                title = stringResource(Strings.download),
                prefixAction = {},
                isSuffixIconVisible = true,
                suffixIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(Drawables.icon_search),
                            contentDescription = null,
                            tint = colorResource(Colors.white)
                        )
                    }
                }

            )
        },
        containerColor = colorResource(Colors.primary)
    ) {

        if (state.movies.isEmpty()) {
            NotAnyComponent(
                imageRes = Drawables.empty_list,
                title = stringResource(Strings.empty_download),
                description = stringResource(Strings.empty_download_description)
            )

        }


    }

}