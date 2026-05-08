package com.example.crocusoft_mova.presentation.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.core.language.AppLanguage
import com.example.crocusoft_mova.presentation.language.components.LanguageHeaderSection
import com.example.crocusoft_mova.presentation.language.components.LanguageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageContent(
    state: LanguageContract.State,
    postIntent: (LanguageContract.Intent) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current



    val suggestedLanguages = remember(state.selectedLanguageCode) {
        listOf(
            AppLanguage.fromCode(state.selectedLanguageCode),
            AppLanguage.EN
        ).distinct()
    }

    val otherLanguages = remember(state.selectedLanguageCode) {
        state.languages.filter { lang -> lang !in suggestedLanguages }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Strings.language),
                            style = BaseTheme.textStyle.t24Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                painter = painterResource(Drawables.back),
                                contentDescription = null,
                                tint = colorResource(Colors.white)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(Colors.primary)
                    )
                )
            },
            containerColor = colorResource(Colors.primary)
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = BaseTheme.dimens.dp6),
                verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp10)
            ) {
                item { LanguageHeaderSection(title = stringResource(Strings.suggested)) }
                items(suggestedLanguages) { language ->
                    LanguageItem(
                        language = language,
                        isSelected = language.code == state.selectedLanguageCode,
                        onClick = {
                            postIntent(LanguageContract.Intent.SelectLanguageCode(context, language.code))
                        }
                    )
                }

                item {
                    HorizontalDivider(thickness = 1.dp, color = colorResource(Colors.dark2))
                }

                item { LanguageHeaderSection(title = stringResource(Strings.language)) }
                items(otherLanguages) { language ->
                    LanguageItem(
                        language = language,
                        isSelected = language.code == state.selectedLanguageCode,
                        onClick = {
                            postIntent(LanguageContract.Intent.SelectLanguageCode(context, language.code))
                        }
                    )
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(Colors.primary).copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colorResource(Colors.secondary))
            }
        }
    }
}