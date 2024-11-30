package com.atylsapp.views.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.atylsapp.R
import com.atylsapp.models.Movie
import com.atylsapp.utils.buildImageUrl

@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onBackClick()
                },
            painter = painterResource(id = R.drawable.ic_back_24),
            tint = Color.Gray,
            contentDescription = ""
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RoundedCornerShape(12.dp)),
                model = buildImageUrl(uiState.movie?.posterPath ?: ""),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = uiState.movie?.title ?: "", fontWeight = FontWeight.W600, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = uiState.movie?.overview ?: "",
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
        }
    }
}
@Preview(device = "id:pixel_9")
@Composable
private fun MovieDetailsPreview() {
    MovieDetailsScreen(
        uiState = MovieDetailsUiState(movie = Movie(
            id = 4508,
            title = "tincidunt",
            overview = "cetero",
            posterPath = null
        )),
        onBackClick = {}
    )
}