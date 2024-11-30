package com.atylsapp.views.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atylsapp.models.Movie
import com.atylsapp.repository.MovieRepo
import com.atylsapp.utils.Status
import com.atylsapp.utils.collectLatest
import com.atylsapp.utils.resourceFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(MovieUiState())
    val movieUiState = _movieUiState

    private var movies: List<Movie>? = listOf()

    init {
        getMovies()
    }

    fun onSearchValueChange(searchTxt: String) {
        if (searchTxt.isEmpty() && movies != null) {
            _movieUiState.update { uiState ->
                uiState.copy(
                    searchTxt = searchTxt,
                    movies = movies ?: listOf()
                )
            }
        } else {
            _movieUiState.update { uiState ->
                uiState.copy(
                    searchTxt = searchTxt,
                    movies = movies?.filter {
                        it.title.contains(searchTxt, ignoreCase = true)
                    } ?: listOf()
                )
            }
        }
    }

    private fun getMovies() {
        resourceFlow {
            movieRepo.getMovies()
        }.collectLatest(viewModelScope) {
            when (it.status) {
                Status.LOADING -> {
                    _movieUiState.update { uiState ->
                        uiState.copy(
                            isLoading = true,
                            isError = false
                        )
                    }
                }

                Status.SUCCESS -> {
                    movies = it.data?.results // Caching the movies data
                    _movieUiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            movies = it.data?.results ?: listOf()
                        )
                    }
                }

                Status.ERROR -> {
                    _movieUiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
            }
        }
    }

    fun retry() {
        getMovies()
    }

}

data class MovieUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val movies: List<Movie> = listOf(),
    val searchTxt: String? = null
)