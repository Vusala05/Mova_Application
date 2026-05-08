package com.example.crocusoft_mova.presentation.auth.pin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.auth.pin.components.OtpInput
import kotlinx.coroutines.flow.SharedFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PinContent(
    postIntent: (PinContract.Intent) -> Unit,
    state: PinContract.State,
    effect: SharedFlow<PinContract.Effect>,
    onNavigateHome: () -> Unit,
    onNavigateBack: () -> Unit,
    isNewPinScreen: Boolean = true,
) {

    val scrollState = rememberScrollState()

    val snackBarHostState = remember { SnackbarHostState() }


    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                PinContract.Effect.NavigateHome -> onNavigateHome()
                is PinContract.Effect.ShowError -> snackBarHostState.showSnackbar(it.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorResource(Colors.primary),
        topBar = {
            if (isNewPinScreen) {
                AppTopBar(
                    prefixAction = onNavigateBack,
                    title = stringResource(Strings.create_new_pin)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BaseTheme.dimens.dp4)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(BaseTheme.dimens.dp6)
            ) {

                if (!isNewPinScreen) {
                    Text(
                        text = state.error,
                        style = BaseTheme.textStyle.t16Bold.copy(color = colorResource(Colors.secondary))
                    )
                }
                Text(
                    text = stringResource(if (isNewPinScreen) Strings.new_pin_description else Strings.enter_pin),
                    style = BaseTheme.textStyle.t18SemiBold
                )

                Spacer(modifier = Modifier.height(BaseTheme.dimens.dp6))


                OtpInput(
                    otpValues = state.pin,
                    onValueChange = { index, pin ->
                        postIntent(
                            PinContract.Intent.SetPin(
                                index,
                                pin
                            )
                        )
                    }

                )
            }

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                action = {
                    if (isNewPinScreen) postIntent(PinContract.Intent.Submit) else postIntent(
                        PinContract.Intent.EnterPin
                    )
                },
                text = stringResource(Strings.btn_continue),
                color = colorResource(Colors.secondary)
            )
        }
    }

}
