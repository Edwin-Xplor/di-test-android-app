package com.example.koinandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinandroidapp.data.network.model.MovieResponse
import com.example.koinandroidapp.usecases.FetchRecommendedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchRecommendedMoviesUseCase
) : ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<MoviesUiState> = _uiState

    init {
        fetchMovieRecommendations()
    }

    private fun fetchMovieRecommendations() {
        viewModelScope.launch {
            fetchNewsUseCase.moviesList
                .catch { exception ->
                    _uiState.value = MoviesUiState.Error(exception)
                }
                // Update View with the latest favorite news
                // Writes to the value property of MutableStateFlow,
                // adding a new element to the flow and updating all
                // of its collectors
                .collect { response ->
                    _uiState.value =
                        response?.movieList?.let { MoviesUiState.Success(it) }
                            ?: MoviesUiState.Error(Throwable("Empty movie list"))
                }
        }
    }
}

// Represents different states for the LatestNews screen
sealed class MoviesUiState {
    data class Success(val movies: List<MovieResponse>) : MoviesUiState()
    data class Error(val exception: Throwable) : MoviesUiState()
    object Loading : MoviesUiState()
}