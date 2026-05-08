package com.example.crocusoft_mova.presentation.dashboard.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.core.MovieCategoryType
import com.example.crocusoft_mova.core.handleResult
import com.example.crocusoft_mova.domain.usecases.GetMoviesByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MovieListContract.Effect>()
    val effect = _effect.asSharedFlow()


    fun onIntent(intent : MovieListContract.Intent){
        when(intent){
            is MovieListContract.Intent.FetchMovies ->{
                _state.update { it.copy(isLoading = true)}
                fetchMovieList(intent.categoryType)

            }
        }
    }

     fun fetchMovieList(categoryType: MovieCategoryType){
        _state.update { it.copy(title = categoryType.title) }
         handleResult(
             useCase = {getMoviesByCategoryUseCase(categoryType)},
             data = { movieList ->
                 _state.update { it.copy(movieList = movieList) }
             },
             loadingState = { loadingState ->
                 _state.update { it.copy(isLoading = loadingState)}
             },
             errorMessage = { message ->
                 _effect.emit(MovieListContract.Effect.ShowError(message))
             }
         )

    }
}