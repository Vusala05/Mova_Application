package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.repository.BookmarkRepository
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(movie: MovieDetailUiModel): ContentState<Unit> {
        return repository.toggleBookmark(movie)
    }
}