package com.example.crocusoft_mova.presentation.auth.choose_interest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.tag.TagEntity
import com.example.crocusoft_mova.domain.usecases.AddTagUseCase
import com.example.crocusoft_mova.domain.usecases.CheckIfUserExistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class ChooseInterestViewModel @Inject constructor(
    private val addTagUseCase: AddTagUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ChooseInterestContract.State())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ChooseInterestContract.Effect>()

    val effect = _effect


    init {
        fetchTags()
    }


    fun onIntent(intent: ChooseInterestContract.Intent) {
        when (intent) {
            is ChooseInterestContract.Intent.ToggleTag -> toggleTag(intent.tagEntity)

            ChooseInterestContract.Intent.Continue -> addTags()
        }
    }


    private fun addTags() {
        viewModelScope.launch {
            when (val res = addTagUseCase(_state.value.selectedTags.map { it.name })) {
                is ContentState.Error<*> -> {
                    _effect.emit(ChooseInterestContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<*> -> {
                    _effect.emit(ChooseInterestContract.Effect.NavigateFillProfile)
                }
            }


        }
    }



    private fun toggleTag(tagEntity: TagEntity) {

        if (_state.value.selectedTags.contains(tagEntity)) {
            viewModelScope.launch {
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags - tagEntity))
            }
        } else {
            viewModelScope.launch {
                _state.emit(_state.value.copy(selectedTags = _state.value.selectedTags + tagEntity))
            }
        }

    }

    private fun fetchTags() {
        viewModelScope.launch {
            val tags = (1..20).map {
                TagEntity(
                    id = it.toString(),
                    name = "Tag $it"
                )
            }
            _state.emit(_state.value.copy(tags = tags))
        }
    }
}