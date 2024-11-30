package com.atylsapp.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieListResponse(val results: List<Movie>)

@Keep
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?
)
