package com.example.crocusoft_mova.presentation.dashboard.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.ProfileUiModel
import com.example.crocusoft_mova.domain.usecases.GetProfileInfoUseCase
import com.example.crocusoft_mova.domain.usecases.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val getProfileInfoUseCase: GetProfileInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileContract.Effect>()
    val effect = _effect.asSharedFlow()


    init {
        loadProfile()
    }

    fun onIntent(intent: ProfileContract.Intent) {
        when (intent) {
            ProfileContract.Intent.LoadProfile -> {
                loadProfile()
            }

            ProfileContract.Intent.LogOut -> {
                logOut()
            }
        }
    }


    private fun logOut() {
        viewModelScope.launch {
            when (val res = logOutUseCase()) {
                is ContentState.Error<*> -> _effect.emit(ProfileContract.Effect.ShowError(res.message))
                is ContentState.Success<*> -> _effect.emit(ProfileContract.Effect.NavigateSignChoice)
            }

        }
    }


    private fun loadProfile() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getProfileInfoUseCase().collect { result ->
                when(result){
                    is ContentState.Error ->{
                        _effect.emit(ProfileContract.Effect.ShowError(result.message))
                    }
                    is ContentState.Success ->{
                        _state.update { it.copy(profile = result.data, isLoading = false) }
                    }
                }


            }
        }
    }
}
