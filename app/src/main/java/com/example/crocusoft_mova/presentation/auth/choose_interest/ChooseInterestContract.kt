package com.example.crocusoft_mova.presentation.auth.choose_interest

import com.example.crocusoft_mova.domain.tag.TagEntity

sealed class ChooseInterestContract {

    sealed interface Intent {
        data class ToggleTag(val tagEntity: TagEntity) : Intent


        data object Continue : Intent
    }

    sealed interface Effect {
        data object NavigateFillProfile : Effect
        data object NavigateHome : Effect
        data class ShowError(val message: String) : Effect
    }

    data class State(
        val tags: List<TagEntity> = emptyList(),
        val selectedTags: Set<TagEntity> = emptySet()
    )
}