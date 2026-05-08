package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import com.example.crocusoft_mova.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(): Flow<ContentState<List<MovieDetailUiModel>>> {
        return repository.getBookmarks()
    }
}