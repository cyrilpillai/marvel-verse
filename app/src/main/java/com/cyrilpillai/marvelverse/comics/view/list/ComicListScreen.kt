package com.cyrilpillai.marvelverse.comics.view.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cyrilpillai.marvelverse.R
import com.cyrilpillai.marvelverse.comics.view.list.model.ComicItem
import com.cyrilpillai.marvelverse.comics.view.list.model.ComicListUiEvent
import com.cyrilpillai.marvelverse.comics.view.list.model.ComicListUiState
import com.cyrilpillai.marvelverse.ui.theme.Red100

@Composable
fun ComicListRoute(
    onComicClicked: (comicId: Int) -> Unit,
    viewModel: ComicListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ComicListScreen(
        state,
        viewModel::onEvent,
        onComicClicked
    )
}

@Composable
fun ComicListScreen(
    state: ComicListUiState,
    onEvent: (event: ComicListUiEvent) -> Unit,
    onComicClicked: (comicId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Red100,
                    )
                )
            )
    ) {
        when (state) {
            is ComicListUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ComicListUiState.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    contentPadding = PaddingValues(12.dp),
                    verticalItemSpacing = 12.dp,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(state.comics) { ComicView(it, onComicClicked) }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun ComicView(
    comicItem: ComicItem,
    onComicClicked: (comicId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = modifier
            .aspectRatio(0.7f)
            .clickable { onComicClicked(comicItem.id) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = comicItem.thumbnailUrl,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "comic image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned {
                        sizeImage = it.size
                    }
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = sizeImage.height.toFloat() / 3,  // 1/3
                            endY = sizeImage.height.toFloat()
                        )
                    )
            )
            Text(
                text = comicItem.title,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
            )
        }
    }
}


@Preview
@Composable
fun ComicListScreenPreview() {
    ComicListScreen(
        state = ComicListUiState.Success(
            comics = listOf(
                ComicItem(
                    id = 201,
                    title = "Marvel Previews (2017)",
                    description = "",
                    thumbnailUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/80/5e3d7536c8ada.jpg"
                )
            )
        ),
        onEvent = {},
        onComicClicked = {}
    )
}