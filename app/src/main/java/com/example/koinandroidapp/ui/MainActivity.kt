package com.example.koinandroidapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.koinandroidapp.data.network.model.MovieResponse
import com.example.koinandroidapp.ui.theme.KoinAndroidAppTheme
import com.example.koinandroidapp.viewmodel.MainViewModel
import com.example.koinandroidapp.viewmodel.MoviesUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    when (uiState) {
                        is MoviesUiState.Error -> {
                            (uiState as MoviesUiState.Error).exception.message?.let {
                                CenteredContent {
                                    Text(text = it)
                                }
                            }
                        }

                        MoviesUiState.Loading -> {
                            CenteredContent { CircularProgressIndicator() }
                        }

                        is MoviesUiState.Success -> {
                            MovieList((uiState as MoviesUiState.Success).movies)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CenteredContent(content: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        content()
    }
}

@Composable
fun MovieList(moviesList: List<MovieResponse>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(moviesList.size) {
            MovieListItem(moviesList[it])
        }
    }
}

@Composable
fun MovieListItem(movieModel: MovieResponse) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        Column {
            SubcomposeAsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movieModel.posterPath}",
                contentDescription = "Poster",
                contentScale = ContentScale.Crop,
                loading = { CenteredContent { CircularProgressIndicator() } },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(8.dp))
            CenteredContent {
                Text(
                    text = movieModel.title ?: "Missing Title",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreview() {
    KoinAndroidAppTheme {
        MovieList(
            listOf(
                MovieResponse(1, "Movie 1", ""),
                MovieResponse(2, "Movie 2 with really really long name", "")
            )
        )
    }
}