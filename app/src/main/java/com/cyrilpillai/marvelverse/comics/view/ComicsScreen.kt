package com.cyrilpillai.marvelverse.comics.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.cyrilpillai.marvelverse.comics.view.model.ComicItem
import com.cyrilpillai.marvelverse.comics.view.model.ComicsUiEvent
import com.cyrilpillai.marvelverse.comics.view.model.ComicsUiState
import com.cyrilpillai.marvelverse.ui.theme.Red100

@Composable
fun ComicsRoute(
    onComicClicked: (comicId: Int) -> Unit,
    viewModel: ComicsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ComicsScreen(
        state,
        viewModel::onEvent,
        onComicClicked
    )
}

@Composable
fun ComicsScreen(
    state: ComicsUiState,
    onEvent: (event: ComicsUiEvent) -> Unit,
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
            is ComicsUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ComicsUiState.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
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

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onComicClicked(comicItem.id) }
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
            maxLines = 1,
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


@Preview
@Composable
fun ComicsScreenPreview() {
    ComicsScreen(
        state = ComicsUiState.Success(
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