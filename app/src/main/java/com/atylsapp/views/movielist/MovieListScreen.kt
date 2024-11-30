package com.atylsapp.views.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun MovieListScreen(
    onMovieClick: (Movie?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        var text by remember { mutableStateOf("") }

        AtlysTextField(
            modifier = Modifier.padding(16.dp),
            text = text,
            hint = "Search Movies",
            onValueChange = {
                text = it
            }
        )

        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(5) {
                MovieItem(onMovieClick = onMovieClick)
            }
        }
    }

}

@Composable
private fun MovieItem(onMovieClick: (Movie?) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable( // Disabling the ripple effect
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onMovieClick(Movie(
                    id = 8252,
                    title = "dolore",
                    overview = "ridiculus",
                    posterPath = null
                ))
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = "",
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "title",
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
        onMovieClick = {}
    )
}