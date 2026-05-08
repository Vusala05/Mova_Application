package com.example.crocusoft_mova.presentation.dashboard.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.handleResult
import com.example.crocusoft_mova.domain.usecases.FetchNowPlayingMoviesUseCase
import com.example.crocusoft_mova.domain.usecases.FetchTopRatedUseCase
import com.example.crocusoft_mova.domain.usecases.FetchUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase,
    private val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase,
    private val fetchTopRatedUseCase: FetchTopRatedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state
        .onStart {
            fetchTopRatedMovies()
            fetchNowPlayingMovies()
            fetchUpcomingMovies()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect = _effect.asSharedFlow()



    fun onIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.FetchNowPlayingMovies -> fetchNowPlayingMovies()
            HomeContract.Intent.FetchUpcomingMovies -> fetchUpcomingMovies()
            HomeContract.Intent.FetchTopRatedMovies -> fetchTopRatedMovies()
        }
    }

    private fun fetchNowPlayingMovies() {
        handleResult(
            useCase = { fetchNowPlayingMoviesUseCase() },
            data = { result ->
                _state.update { it.copy(nowPlayingMovies = result) }
            },
            loadingState = { loadingState ->
                _state.update { it.copy(isNowPlayingLoading = loadingState) }
            },
            errorMessage = { message ->
                _effect.emit(HomeContract.Effect.ShowError(message))
            }
        )
    }

    private fun fetchUpcomingMovies() {
            handleResult(
                useCase = { fetchUpcomingMoviesUseCase() },
                data = { result ->
                    _state.update { it.copy(upcomingMovies = result) }
                },
                loadingState = { loadingState ->
                    _state.update { it.copy(isUpcomingLoading = loadingState) }
                },
                errorMessage = { message ->
                    _effect.emit(HomeContract.Effect.ShowError(message))
                }
            )
    }

    private fun fetchTopRatedMovies() {
        handleResult(
            useCase = { fetchTopRatedUseCase() },
            data = { result ->
                _state.update { it.copy(topRatedMovies = result) }
            },
            loadingState = { loadingState ->
                _state.update { it.copy(isTopRatedLoading = loadingState) }
            },
            errorMessage = { message ->
                _effect.emit(HomeContract.Effect.ShowError(message))
            }
        )
    }
}