package com.atylsapp.network

import com.atylsapp.models.MovieListResponse
import retrofit2.http.GET


interface MovieApiService {

    @GET("trending/movie/day")
    suspend fun getMovies(): MovieListResponse
}
