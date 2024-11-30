package com.atylsapp.repository

import com.atylsapp.models.MovieListResponse
import com.atylsapp.network.MovieApiService
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val movieApiService: MovieApiService,
) : MovieRepo {
    override suspend fun getMovies() : MovieListResponse {
        return movieApiService.getMovies()
    }
}