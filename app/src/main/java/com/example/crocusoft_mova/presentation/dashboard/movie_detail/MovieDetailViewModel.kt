package com.example.crocusoft_mova.presentation.dashboard.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.handleResult
import com.example.crocusoft_mova.domain.usecases.CheckBookmarkStatusUseCase
import com.example.crocusoft_mova.domain.usecases.FetchMovieDetailUseCase
import com.example.crocusoft_mova.domain.usecases.FetchSimilarUseCase
import com.example.crocusoft_mova.domain.usecases.FetchVideosUseCase
import com.example.crocusoft_mova.domain.usecases.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchSimilarUseCase: FetchSimilarUseCase,
    private val fetchVideosUseCase: FetchVideosUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val checkBookmarkStatusUseCase: CheckBookmarkStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MovieDetailContract.Effect>()
    val effect = _effect.asSharedFlow()


    fun onIntent(intent: MovieDetailContract.Intent) {
        when (intent) {
            is MovieDetailContract.Intent.FetchMovieDetail -> {
                fetchAllData(intent.movieId)
                checkBookmarkStatus(intent.movieId)
            }
            is MovieDetailContract.Intent.OnTabSelected -> {
                _state.update { it.copy(selectedTab = intent.index) }
            }
            is MovieDetailContract.Intent.OnBookMarkClick -> {
                toggleBookmark()
            }

        }
    }

    private fun checkBookmarkStatus(movieId : Int){
        viewModelScope.launch {
            checkBookmarkStatusUseCase(movieId).collect { isBookmarked->
                _state.update { it.copy(isBookMarked = isBookmarked) }
            }
        }
    }

    private fun toggleBookmark() {
        val currentMovie = _state.value.movieDetail
        if (currentMovie.id == 0) return
        viewModelScope.launch {
            val result = toggleBookmarkUseCase(currentMovie)
            if (result is ContentState.Error) {
                _effect.emit(MovieDetailContract.Effect.ShowError(result.message))
            }

        }
    }
    private fun fetchAllData(movieId: Int) {
        fetchMovieDetailById(movieId)
        fetchSimilarMovies(movieId)
        fetchMovieVideos(movieId)
    }

    private fun fetchMovieDetailById(movieId: Int) {
        viewModelScope.launch {
            when (val res = fetchMovieDetailUseCase(movieId)) {
                is ContentState.Success -> {
                    _state.update { it.copy(movieDetail = res.data) }
                }
                is ContentState.Error -> {
                    _effect.emit(MovieDetailContract.Effect.ShowError(res.message))
                }

            }
        }
    }

    private fun fetchSimilarMovies(movieId: Int) {
        handleResult(
            useCase = {fetchSimilarUseCase(movieId)},
            data = { similarMovies ->
                _state.update { it.copy(similarMovies = similarMovies) }
            },
            errorMessage = { message ->
                _effect.emit(MovieDetailContract.Effect.ShowError(message))
            },
            loadingState = {}
        )
    }
    private fun fetchMovieVideos(movieId: Int) {
        handleResult(
            useCase = {fetchVideosUseCase(movieId)},
            data = { videos ->
                _state.update { it.copy(trailers = videos) }
            },
            errorMessage = { message ->
                _effect.emit(MovieDetailContract.Effect.ShowError(message))
            },
            loadingState = {}
        )
    }
}