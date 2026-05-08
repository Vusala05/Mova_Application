package com.example.crocusoft_mova.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.constants.AppErrors
import kotlinx.coroutines.launch

fun <T> ViewModel.handleResult(
    useCase : suspend () -> ContentState<List<T>>,
    data : (List<T>) -> Unit,
    loadingState : (Boolean) -> Unit,
    errorMessage : suspend (String) -> Unit
){
    viewModelScope.launch {
        try {
            loadingState(true)
            when(val res = useCase.invoke()){
                is ContentState.Error -> {
                    errorMessage(res.message)
                }
                is ContentState.Success -> {
                   data(res.data)
                }
            }
        } catch (e : Exception){
            errorMessage(e.message ?: AppErrors.unknownError)
        } finally {
            loadingState(false)
        }


    }
}

