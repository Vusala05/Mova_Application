package com.example.crocusoft_mova.presentation.auth.signin

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.crocusoft_mova.presentation.auth.components.SignContent
import com.example.crocusoft_mova.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.SharedFlow


@Composable
fun SignInContent(
    postIntent: (SignInContract.Intent) -> Unit,
    state: SignInContract.State,
    effect: SharedFlow<SignInContract.UiEffect>,
    onNavigateSignUp: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateToChooseInterest: () -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {

        effect.collect {
            when (it) {
                SignInContract.UiEffect.NavigateToChoose -> onNavigateToChooseInterest()

                is SignInContract.UiEffect.ShowError -> {
                    snackBarHostState.showSnackbar(it.message)
                }

                SignInContract.UiEffect.NavigateHome -> onNavigateHome()
            }

        }

    }

    SignContent(
        email = state.email,
        password = state.password,
        onEmailChange = { postIntent(SignInContract.Intent.SetEmail(it)) },
        onPasswordChange = { postIntent(SignInContract.Intent.SetPassword(it)) },
        onSetChecked = { postIntent(SignInContract.Intent.SetChecked(it)) },
        onSubmit = { postIntent(SignInContract.Intent.Submit) },
        isLoading = state.isLoading,
        checked = state.checked,
        onNavigate = onNavigateSignUp,
        isSignUp = false,
        onNavigateBack = onNavigateBack,
        snackbarHostState = snackBarHostState
    )
}