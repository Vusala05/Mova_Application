package com.example.crocusoft_mova.presentation.auth.fill_profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.common.components.AppTextField
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.GenderSelector
import com.example.crocusoft_mova.common.components.ProfileAvatar
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Strings
import kotlinx.coroutines.flow.SharedFlow
import java.security.Key


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FillProfileContent(
    state: FillProfileContract.State,
    postIntent: (FillProfileContract.Intent) -> Unit,
    effect: SharedFlow<FillProfileContract.Effect>,
    onNavigateBack: () -> Unit,
    onNavigatePin: () -> Unit,
) {

    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                FillProfileContract.Effect.NavigatePin -> onNavigatePin()
                FillProfileContract.Effect.NavigateProfile -> onNavigateBack()
                is FillProfileContract.Effect.ShowError -> snackBarHostState.showSnackbar(it.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(prefixAction = {
                onNavigateBack()
            },
                title = if(!state.isEditMode) stringResource(Strings.fill_profile) else "Edit Profile")
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BaseTheme.dimens.dp4)
            ) {
                if (state.isEditMode) {
                    AppButton(
                        modifier = Modifier.fillMaxWidth(),
                        action = { postIntent(FillProfileContract.Intent.Submit) },
                        text = "Update",
                        color = colorResource(Colors.secondary)
                    )
                } else {
                    AppButton(
                        modifier = Modifier.weight(1f),
                        action = { postIntent(FillProfileContract.Intent.Skip) },
                        text = stringResource(Strings.skip),
                        color = colorResource(Colors.dark2)
                    )
                    AppButton(
                        modifier = Modifier.weight(1f),
                        action = { postIntent(FillProfileContract.Intent.Submit) },
                        text = stringResource(Strings.btn_continue),
                        color = colorResource(Colors.secondary)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(BaseTheme.dimens.dp4)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = BaseTheme.dimens.dp10)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp4)

            ) {

                ProfileAvatar(
                    avatar = state.imgUri,
                    onClick = { postIntent(FillProfileContract.Intent.SetProfile(it)) }
                )

                AppTextField(
                    value = state.fullName,
                    onValueChange = { postIntent(FillProfileContract.Intent.SetFullName(it)) },
                    placeholder = stringResource(Strings.full_name)
                )
                AppTextField(
                    value = state.nickName,
                    onValueChange = { postIntent(FillProfileContract.Intent.SetNickName(it)) },
                    placeholder = stringResource(Strings.nick_name)
                )
                AppTextField(
                    value = state.phoneNumber,
                    onValueChange = { postIntent(FillProfileContract.Intent.SetPhoneNumber(it)) },
                    placeholder = stringResource(Strings.phone_number),
                    keyboardType = KeyboardType.Number
                )
                GenderSelector(
                    value = state.gender,
                    onGenderSelected = { postIntent(FillProfileContract.Intent.SetGender(it)) }
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp2),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                if (state.isEditMode) {

                    AppButton(
                        modifier = Modifier.fillMaxWidth(),
                        action = { postIntent(FillProfileContract.Intent.Submit) },
                        text = "Update",
                        color = colorResource(Colors.secondary)
                    )
                } else {
                    AppButton(
                        modifier = Modifier.weight(1f),
                        action = {
                            postIntent(FillProfileContract.Intent.Skip)
                        },
                        text = stringResource(Strings.skip),
                        color = colorResource(Colors.dark2)
                    )

                    AppButton(
                        modifier = Modifier.weight(1f),
                        action = {
                            postIntent(FillProfileContract.Intent.Submit)
                        },
                        text = stringResource(Strings.btn_continue),
                        color = colorResource(Colors.secondary)
                    )
                }
            }
        }
    }


}