package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.models.MovieDetailUiModel
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun toggleBookmark(movie: MovieDetailUiModel) : ContentState<Unit>

    fun getBookmarks() : Flow<ContentState<List<MovieDetailUiModel>>>

    fun isBookmarked(movieId : Int) : Flow<Boolean>
}