package com.example.crocusoft_mova.presentation.auth.signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

import com.example.crocusoft_mova.presentation.auth.components.SignContent

import kotlinx.coroutines.flow.SharedFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpContent(
    state: SignUpContract.State,
    onNavigate: () -> Unit,
    postIntent: (SignUpContract.Intent) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToChooseInterest: () -> Unit,
    effect: SharedFlow<SignUpContract.UiEffect>
) {



    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                SignUpContract.UiEffect.NavigateToChoose -> onNavigateToChooseInterest()
                is SignUpContract.UiEffect.ShowError -> {
                    snackbarHostState.showSnackbar(it.message)
                }
            }
        }
    }

    SignContent(
        email = state.email,
        password = state.password,
        onEmailChange = { postIntent(SignUpContract.Intent.SetEmail(it)) },
        onPasswordChange = { postIntent(SignUpContract.Intent.SetPassword(it)) },
        onSetChecked = { postIntent(SignUpContract.Intent.SetChecked(it)) },
        onSubmit = { postIntent(SignUpContract.Intent.Submit) },
        isLoading = state.isLoading,
        checked = state.checked,
        isSignUp = true,
        onNavigate = onNavigate,
        onNavigateBack = onNavigateBack,
        snackbarHostState = snackbarHostState

        )
}


