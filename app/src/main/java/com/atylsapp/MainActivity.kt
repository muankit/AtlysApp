package com.atylsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atylsapp.navigation.MovieDetailsRoute
import com.atylsapp.navigation.MovieListRoute
import com.atylsapp.ui.theme.AtylsAppTheme
import com.atylsapp.views.moviedetails.MovieDetailsScreen
import com.atylsapp.views.moviedetails.MovieDetailsViewModel
import com.atylsapp.views.movielist.MovieListScreen
import com.atylsapp.views.movielist.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AtylsAppTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = MovieListRoute
                    ) {
                        composable<MovieListRoute> {
                            val viewModel by viewModels<MovieViewModel>()
                            val uiState by viewModel.movieUiState.collectAsStateWithLifecycle()

                            MovieListScreen(
                                uiState = uiState,
                                onSearchValueChange = viewModel::onSearchValueChange,
                                onMovieClick = { movie ->
                                    movie?.let {
                                        navController.navigate(MovieDetailsRoute(movie))
                                    }
                                },
                                onRetryClicked = viewModel::retry
                            )
                        }

                        composable<MovieDetailsRoute>(
                            typeMap = MovieDetailsRoute.typeMap
                        ) {
                            val viewModel = viewModel<MovieDetailsViewModel>()
                            val uiState by viewModel.movieDetailsUiState.collectAsStateWithLifecycle()

                            MovieDetailsScreen(
                                uiState = uiState,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
