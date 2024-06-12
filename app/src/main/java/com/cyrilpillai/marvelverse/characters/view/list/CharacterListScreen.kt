package com.cyrilpillai.marvelverse.characters.view.list

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.cyrilpillai.marvelverse.R
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterItem
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterListUiEvent
import com.cyrilpillai.marvelverse.ui.theme.Red100
import kotlinx.coroutines.flow.flowOf

@Composable
fun CharacterListRoute(
    onCharacterClicked: (characterId: Int) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characterItems = viewModel.characterItemFlow.collectAsLazyPagingItems()

    CharacterListScreen(
        characterItems,
        viewModel::onEvent,
        onCharacterClicked
    )
}

@Composable
fun CharacterListScreen(
    characterItems: LazyPagingItems<CharacterItem>,
    onEvent: (event: CharacterListUiEvent) -> Unit,
    onCharacterClicked: (characterId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = characterItems.loadState) {
        if (characterItems.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: ${(characterItems.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

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
        if (characterItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                verticalItemSpacing = 12.dp,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    count = characterItems.itemCount,
                    key = characterItems.itemKey { it.id }
                ) { index ->
                    characterItems[index]?.let {
                        CharacterView(characterItem = it, onCharacterClicked = onCharacterClicked)
                    }
                }

                if (characterItems.loadState.append is LoadState.Loading) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterView(
    characterItem: CharacterItem,
    onCharacterClicked: (characterId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        onClick = { onCharacterClicked(characterItem.id) },
        modifier = modifier
            .aspectRatio(0.8f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = characterItem.thumbnailUrl,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "character image",
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
                text = characterItem.name,
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
}


@Preview
@Composable
fun CharacterListScreenPreview() {
    CharacterListScreen(
        characterItems = flowOf(
            PagingData.from(
                listOf(
                    CharacterItem(
                        id = 101,
                        name = "Avengers",
                        description = """Earth's Mightiest Heroes joined forces to take on 
                        |threats that were too big for any one hero to tackle. With a 
                        |roster that has included Captain America, Iron Man, Ant-Man, Hulk, 
                        |Thor, Wasp and dozens more over the years, the Avengers have come 
                        |to be regarded as Earth's No. 1 team.""".trimMargin(),
                        thumbnailUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7.jpg"
                    )
                ),
                sourceLoadStates =
                LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        ).collectAsLazyPagingItems(),
        onEvent = {},
        onCharacterClicked = {}
    )
}