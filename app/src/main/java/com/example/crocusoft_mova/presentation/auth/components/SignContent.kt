package com.example.crocusoft_mova.presentation.auth.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppCheckbox
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.ClickableText
import com.example.crocusoft_mova.common.components.DividerWithText
import com.example.crocusoft_mova.core.BaseTextStyle
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.auth.signup.components.SignUpWithList


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSetChecked: (Boolean) -> Unit,
    onNavigate: () -> Unit,
    onSubmit: () -> Unit,
    isLoading: Boolean,
    checked: Boolean,
    snackbarHostState: SnackbarHostState? = null,
    isSignUp: Boolean = true,
    onNavigateBack: () -> Unit = {},


    ) {

    val scrollState = rememberScrollState()




    Scaffold(
        snackbarHost = { if (snackbarHostState != null) SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            AppTopBar(prefixAction = onNavigateBack)
        },
        containerColor = colorResource(Colors.primary),
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = BaseTheme.dimens.dp5),
        ) {

            Spacer(Modifier.height(BaseTheme.dimens.dp12))


            Icon(
                painter = painterResource(Drawables.logo),
                contentDescription = null,
                tint = colorResource(Colors.secondary),
            )


            Spacer(Modifier.height(BaseTheme.dimens.dp6))


            Text(
                text = stringResource(if (isSignUp) Strings.createAccount else Strings.login),
                style = BaseTextStyle.t36Bold
            )


            Spacer(Modifier.height(BaseTheme.dimens.dp8))

            AppTextField(
                prefixIcon = Drawables.inbox,
                value = email,
                onValueChange = onEmailChange,
                placeholder = stringResource(Strings.email)
            )


            Spacer(Modifier.height(BaseTheme.dimens.dp4))

            AppTextField(
                prefixIcon = Drawables.lock,
                value = password,
                onValueChange = onPasswordChange,
                isPasswordField = true,
                placeholder = stringResource(Strings.password)
            )


            Spacer(Modifier.height(BaseTheme.dimens.dp5))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                AppCheckbox(
                    checked = checked
                ) {
                    onSetChecked(it)
                }

                Spacer(Modifier.width(BaseTheme.dimens.dp3))

                Text(
                    text = stringResource(Strings.remember),
                    style = BaseTheme.textStyle.t14SemiBold
                )
            }


            Spacer(Modifier.height(BaseTheme.dimens.dp6))


            AppButton(
                isLoading = isLoading,
                action = onSubmit,
                text = stringResource(if (isSignUp) Strings.sign_up else Strings.sign_in),
            )


            Spacer(Modifier.height(BaseTheme.dimens.dp6))

            if (!isSignUp) {

                Text(
                    text = stringResource(Strings.forgot),
                    style = BaseTheme.textStyle.t16SemiBoldRed
                )


                Spacer(Modifier.height(BaseTheme.dimens.dp6))

            }



            DividerWithText(" or continue with ")


            Spacer(Modifier.height(BaseTheme.dimens.dp6))


            SignUpWithList()


            Spacer(Modifier.height(BaseTheme.dimens.dp6))


            ClickableText(
                preText = stringResource(if (isSignUp) Strings.already else Strings.donthave),
                sufText = stringResource(if (isSignUp) Strings.sign_in else Strings.sign_up)
            ) {
                onNavigate()
            }

            Spacer(Modifier.height(BaseTheme.dimens.dp10))
        }
    }
}