package com.atylsapp.views.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atylsapp.models.Movie
import com.atylsapp.repository.MovieRepo
import com.atylsapp.utils.Status
import com.atylsapp.utils.collectLatest
import com.atylsapp.utils.resourceFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private var movies: List<Movie>? = listOf()

    init {
        getMovies()
    }

    private fun getMovies() {
        resourceFlow {
            movieRepo.getMovies()
        }.collectLatest(viewModelScope) {
            when (it.status) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    movies = it.data?.results // Caching the movies data
                    println("Movies are: $movies")

                }

                Status.ERROR -> {

                }
            }
        }
    }

}