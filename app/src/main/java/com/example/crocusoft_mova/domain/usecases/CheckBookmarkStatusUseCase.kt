package com.example.crocusoft_mova.domain.usecases

import com.example.crocusoft_mova.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckBookmarkStatusUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(movieId: Int): Flow<Boolean> {
        return repository.isBookmarked(movieId)
    }
}