package com.example.crocusoft_mova.presentation.dashboard.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.ProfileAvatar
import com.example.crocusoft_mova.common.components.VerticalSpacer
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.profile.components.SettingsItem
import com.example.crocusoft_mova.presentation.dashboard.profile.components.SettingsItemModel
import kotlinx.coroutines.flow.SharedFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileContent(
    state: ProfileContract.State,
    effect: SharedFlow<ProfileContract.Effect>,
    postIntent: (ProfileContract.Intent) -> Unit,
    onNavigateSignChoice: () -> Unit,
    onNavigateToLanguage: () -> Unit,
    onNavigateFillProfile: (Boolean) -> Unit,
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }

    val settingItems = listOf(
        SettingsItemModel(
            icon = Drawables.profile,
            titleRes = Strings.edit_profile,
            action = { onNavigateFillProfile(true) }
        ),
        SettingsItemModel(
            icon = Drawables.icon_notification,
            titleRes = Strings.notification
        ),
        SettingsItemModel(
            icon = Drawables.download,
            titleRes = Strings.download
        ),
        SettingsItemModel(
            icon = Drawables.icon_help,
            titleRes = Strings.help_center
        ),
        SettingsItemModel(
            icon = Drawables.language_icon,
            titleRes = Strings.change_language,
            action = { onNavigateToLanguage() }
        ),

        SettingsItemModel(
            icon = Drawables.mode,
            titleRes = Strings.mode,
            isSwitch = true,
            switchChecked = isDarkMode,
            onCheckedChange = { isChecked ->
                onDarkModeChange(isChecked)
            }
        ),

        SettingsItemModel(
            icon = Drawables.icon_help,
            titleRes = Strings.log_out,
            action = {
                postIntent(ProfileContract.Intent.LogOut)
            }
        )
    )

    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                ProfileContract.Effect.NavigateSignChoice -> onNavigateSignChoice()
                is ProfileContract.Effect.ShowError -> snackBarHostState.showSnackbar(it.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            AppTopBar(
                modifier = Modifier.padding(top = BaseTheme.dimens.dp2),
                prefixIcon = Drawables.logo,
                prefixColor = Colors.secondary,
                title = stringResource(Strings.profile),
                prefixAction = {}
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(Colors.primary).copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(BaseTheme.dimens.dp5)
                .padding(padding)
        ) {

            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = BaseTheme.dimens.dp8)
                ) {
                    ProfileAvatar(avatar = null)

                    Text(
                        text = state.profile.fullName,
                        style = BaseTheme.textStyle.t20Bold
                    )

                    Text(
                        text = state.profile.email,
                        style = BaseTheme.textStyle.t14SemiBold
                    )
                }
            }

            item {
                VerticalSpacer(BaseTheme.dimens.dp6)
            }

            items(settingItems.size) { index ->
                SettingsItem(item = settingItems[index])
            }

            item {
                VerticalSpacer(BaseTheme.dimens.dp6)
            }
        }
    }
}