package com.example.crocusoft_mova.core

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object UiEventBus {
    private val _events = MutableSharedFlow<UiEvent>(replay = 0)
    val events : SharedFlow<UiEvent> = _events.asSharedFlow()

    suspend fun emit(event : UiEvent){
        _events.emit(event)
    }

    suspend fun collectEvent(action: suspend (UiEvent) -> Unit){
        events.collect { event ->
            action(event)
        }
    }
}