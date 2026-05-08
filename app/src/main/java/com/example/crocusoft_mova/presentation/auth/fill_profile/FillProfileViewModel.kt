package com.example.crocusoft_mova.presentation.auth.fill_profile

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.FillProfileUseCase
import com.example.crocusoft_mova.domain.usecases.FillProfileUseCase.*
import com.example.crocusoft_mova.domain.usecases.GetProfileInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.core.net.toUri


@HiltViewModel
class FillProfileViewModel @Inject constructor(
    private val fillProfileUseCase: FillProfileUseCase,
    private val getProfileInfoUseCase: GetProfileInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FillProfileContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<FillProfileContract.Effect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(intent: FillProfileContract.Intent) {
        when (intent) {

            is FillProfileContract.Intent.SetFullName -> {
                _state.update { it.copy(fullName = intent.fullName) }

            }

            is FillProfileContract.Intent.SetNickName -> {

                _state.update { it.copy(nickName = intent.nickName) }

            }

            is FillProfileContract.Intent.SetGender -> {

                _state.update { it.copy(gender = intent.gender) }

            }

            is FillProfileContract.Intent.SetPhoneNumber -> {

                _state.update { it.copy(phoneNumber = intent.phoneNumber) }

            }

            is FillProfileContract.Intent.SetProfile -> {

                _state.update { it.copy(imgUri = intent.uri) }

            }

            FillProfileContract.Intent.Submit -> submit()

            FillProfileContract.Intent.Skip -> {
                viewModelScope.launch {
                    _effect.emit(FillProfileContract.Effect.NavigatePin)
                }
            }
        }
    }
    fun setEditMode(isEdit: Boolean) {
        _state.update { it.copy(isEditMode = isEdit) }
        loadExistingUserData()
    }

    private fun loadExistingUserData() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
           getProfileInfoUseCase().collect { result ->
                when(result){
                    is ContentState.Error -> {
                        _effect.emit(FillProfileContract.Effect.ShowError(result.message))
                        _state.update { it.copy(isLoading = false) }

                    }
                    is ContentState.Success -> {
                        val data = result.data
                        _state.update {
                            it.copy(
                                fullName = data.fullName,
                                nickName = data.nickName,
                                phoneNumber = data.phoneNumber,
                                gender = data.gender,
                                imgUri = if (data.imageUri.isNotEmpty()) data.imageUri.toUri() else null
                            )
                        }
                        _state.update { it.copy(isLoading = false) }

                    }
            }

            }
        }
    }

    private fun submit() {
        viewModelScope.launch {
            when (val res = fillProfileUseCase.invoke(
                Params(
                    fullName = state.value.fullName,
                    gender = state.value.gender,
                    nickname = state.value.nickName,
                    phoneNumber = state.value.phoneNumber,
                    profileUri = ""
                )
            )) {
                is ContentState.Error<*> -> _effect.emit(

                    FillProfileContract.Effect.ShowError(
                        res.message
                    )

                )

                is ContentState.Success<*> -> {
                    if (state.value.isEditMode) {
                        _effect.emit(FillProfileContract.Effect.NavigateProfile)
                    }
                    else{
                        _effect.emit(FillProfileContract.Effect.NavigatePin)
                    }
                }
            }

        }
    }
}