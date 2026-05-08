package com.example.crocusoft_mova.presentation.auth.pin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.CreatePinUseCase
import com.example.crocusoft_mova.domain.usecases.EnterPinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class PinViewModel @Inject constructor(
    private val createPinUseCase: CreatePinUseCase,
    private val enterPinUseCase: EnterPinUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow(PinContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PinContract.Effect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: PinContract.Intent) {
        when (intent) {
            is PinContract.Intent.SetPin -> {
                _state.update { it.copy(error = "") }
                viewModelScope.launch {
                    val newPin = _state.value.pin.toMutableList()
                    newPin[intent.index] = intent.pin
                    _state.update { it.copy(pin = newPin) }
                }

                Log.i("pin", state.value.pin.toString())
            }

            PinContract.Intent.Submit -> {
                createPin()
            }

            PinContract.Intent.EnterPin -> {
                enterPin()
            }
        }

    }

    private fun enterPin() {

        viewModelScope.launch {
            when (val res = enterPinUseCase(_state.value.pin.joinToString(separator = ""))) {
                is ContentState.Error<*> -> {
                    _state.update { it.copy(error = res.message) }

                }

                is ContentState.Success<*> -> {
                    _state.update { it.copy(error = "") }
                    _effect.emit(PinContract.Effect.NavigateHome)
                }
            }
        }
    }

    private fun createPin() {
        viewModelScope.launch {
            when (val res = createPinUseCase(_state.value.pin.joinToString(separator = ""))) {
                is ContentState.Error<*> -> {
                    _effect.emit(PinContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<*> -> {
                    _effect.emit(PinContract.Effect.NavigateHome)
                }
            }


        }
    }

}