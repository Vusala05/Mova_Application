package com.example.crocusoft_mova.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _state = MutableStateFlow(SignUpContract.State())

    val state = _state.asStateFlow()


    private val _effect = MutableSharedFlow<SignUpContract.UiEffect>()

    val effect = _effect.asSharedFlow()


    fun onIntent(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.SetEmail ->
                _state.update { it.copy(email = intent.email) }


            is SignUpContract.Intent.SetPassword -> _state.update { it.copy(password = intent.password) }


            SignUpContract.Intent.Submit -> signUp()


            is SignUpContract.Intent.SetChecked -> {
                _state.update { it.copy(checked = intent.checked) }

            }
        }
    }

    private fun signUp() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {

            when (val res = signUpUseCase(_state.value.email, _state.value.password)) {
                is ContentState.Error<*> -> {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(SignUpContract.UiEffect.ShowError(res.message))

                }

                is ContentState.Success<*> -> {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(SignUpContract.UiEffect.NavigateToChoose)
                }
            }

        }
    }

}

