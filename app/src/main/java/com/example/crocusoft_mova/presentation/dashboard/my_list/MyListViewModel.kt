package com.example.crocusoft_mova.presentation.dashboard.my_list

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocusoft_mova.core.ContentState
import com.example.crocusoft_mova.domain.usecases.CheckBookmarkStatusUseCase
import com.example.crocusoft_mova.domain.usecases.GetBookmarksUseCase
import com.example.crocusoft_mova.domain.usecases.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getBookmarksUseCase: GetBookmarksUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(MyListContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MyListContract.Effect>()
    val effect = _effect.asSharedFlow()

    init {
        onIntent(MyListContract.Intent.GetMarkedMovies)
    }

    fun onIntent(intent: MyListContract.Intent){
      when(intent){
          is MyListContract.Intent.GetMarkedMovies ->{
             getToggleMovies()
          }
          is MyListContract.Intent.OnSelectCategory ->{
            _state.update {it.copy(selectedCategory = intent.category)}
              updateFilteredMovies(intent.category)
          }
          is MyListContract.Intent.OnMovieClick -> {
              viewModelScope.launch {
                  _effect.emit(MyListContract.Effect.NavigateToDetail(intent.movieId))
              }
          }
      }
    }

    fun getToggleMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getBookmarksUseCase().collect { result ->
                when(result){
                    is ContentState.Error -> {
                        _state.update { it.copy(isLoading = false) }
                        _effect.emit(MyListContract.Effect.ShowError(result.message))
                    }
                    is ContentState.Success->{
                        _state.update { it.copy(
                            movies = result.data,
                            isLoading = false)
                        }
                        updateFilteredMovies(_state.value.selectedCategory)
                    }
                }
            }
        }
    }
    private fun updateFilteredMovies(category : String){
        _state.update { currentState ->
            val filtered = if(category == "All Categories"){
                currentState.movies
            } else{
                currentState.movies.filter { it.mediaType.equals(category, ignoreCase = true) }

            }
            currentState.copy(
                selectedCategory = category,
                filteredMovies = filtered
            )
        }
    }

}