package com.example.crocusoft_mova.presentation.auth.login_choice

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.ClickableText
import com.example.crocusoft_mova.common.components.DividerWithText
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.auth.login_choice.components.LoginChoicesList



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginChoiceContent(
    onNavigateSignIn: () -> Unit,
    onNavigateSignUp: () -> Unit,
    onNavigateBack: ()->Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = colorResource(Colors.primary),

        topBar = {
            AppTopBar(
                prefixAction = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(BaseTheme.dimens.dp4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            Image(
                painterResource(Drawables.login_choice), contentDescription = null,
                modifier = Modifier.height(BaseTheme.dimens.image_height).width(BaseTheme.dimens.image_width),
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            Text(
                text = stringResource(Strings.lets_in),
                style = BaseTheme.textStyle.t48Bold
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            LoginChoicesList()

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            DividerWithText(" or ")

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            AppButton(
                action = onNavigateSignIn,
                text = stringResource(Strings.sign_password)
            )

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp4))

            ClickableText(
                preText = stringResource(Strings.donthave),
                sufText = stringResource(Strings.sign_up)
            ) {
                onNavigateSignUp()
            }

        }
    }

}