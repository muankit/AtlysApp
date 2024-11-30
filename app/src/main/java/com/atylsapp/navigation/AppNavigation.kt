package com.atylsapp.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.atylsapp.models.Movie
import com.atylsapp.utils.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object MovieListRoute

@Serializable
data class MovieDetailsRoute(
    val movie: Movie
) {
    companion object {
        val typeMap = mapOf(typeOf<Movie>() to serializableType<Movie>())

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<MovieDetailsRoute>(typeMap)
    }
}
