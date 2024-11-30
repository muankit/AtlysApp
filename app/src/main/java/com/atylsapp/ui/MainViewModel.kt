package com.atylsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atylsapp.repository.MovieRepo
import com.atylsapp.utils.Status
import com.atylsapp.utils.collectLatest
import com.atylsapp.utils.resourceFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    init {

    }

    fun getMovies() {
        resourceFlow {
            movieRepo.getMovies()
        }.collectLatest(viewModelScope) {
            when(it.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    println("Movies are: ${it.data}")
                }
                Status.ERROR -> {}
            }
        }
    }
}