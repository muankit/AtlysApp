package com.atylsapp.views.moviedetails

import androidx.annotation.Keep
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.atylsapp.models.Movie
import com.atylsapp.navigation.MovieDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsUiState = MutableStateFlow(MovieDetailsUiState())
    val movieDetailsUiState = _movieDetailsUiState

    init {
        val movie = MovieDetailsRoute.from(savedStateHandle).movie
        _movieDetailsUiState.value = MovieDetailsUiState(
            movie = movie
        )
    }
}

@Keep
data class MovieDetailsUiState(
    val movie: Movie? = null
)

