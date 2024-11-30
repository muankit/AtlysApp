package com.atylsapp.repository

import com.atylsapp.models.MovieListResponse

interface MovieRepo {
    suspend fun getMovies(): MovieListResponse
}