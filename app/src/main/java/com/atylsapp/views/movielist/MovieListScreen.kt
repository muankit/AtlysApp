package com.atylsapp.views.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.atylsapp.models.Movie
import com.atylsapp.utils.AtlysTextField
import com.atylsapp.utils.buildImageUrl

@Composable
fun MovieListScreen(
    uiState: MovieUiState,
    onSearchValueChange: (String) -> Unit,
    onMovieClick: (Movie?) -> Unit,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        AtlysTextField(
            modifier = Modifier.padding(16.dp),
            text = uiState.searchTxt ?: "",
            hint = "Search Movies",
            enabled = !uiState.isLoading && !uiState.isError,
            onValueChange = onSearchValueChange,

            )

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading ....")
            }
        } else if (uiState.isError) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "We got some error. Please retry!")
                Spacer(Modifier.height(8.dp))
                Button(onClick = onRetryClicked) {
                    Text(text = "Retry")
                }
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(uiState.movies) { movie ->
                    MovieItem(movie = movie, onMovieClick = onMovieClick)
                }
            }
        }
    }

}

@Composable
fun MovieItem(movie: Movie?, onMovieClick: (Movie?) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable( // Disabling the ripple effect
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onMovieClick(movie)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = buildImageUrl(movie?.posterPath ?: ""),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movie?.title ?: "",
            maxLines = 1,
            fontWeight = FontWeight.W600,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }

}


@Preview(device = "id:pixel_9")
@Composable
private fun MovieListPreview() {
    MovieListScreen(
        uiState = MovieUiState(
            isLoading = false,
            isError = false,
            movies = listOf(
                Movie(
                    id = 1241982,
                    title = "Moana 2",
                    posterPath = "/4YZpsylmjHbqeWzjKpUEF8gcLNW.jpg",
                    overview = "nonumy"
                ),
                Movie(
                    id = 1234811,
                    title = "Our Little Secret",
                    posterPath = "/7isqmWUryG2xksrw0E75m3vYTFd.jpg",
                    overview = "nonumy"
                )
            ),
            searchTxt = null
        ),
        onMovieClick = {},
        onSearchValueChange = {},
        onRetryClicked = {}
    )
}