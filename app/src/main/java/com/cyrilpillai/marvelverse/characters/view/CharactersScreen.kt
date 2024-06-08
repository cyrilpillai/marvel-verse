package com.cyrilpillai.marvelverse.characters.view

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
import com.cyrilpillai.marvelverse.characters.view.model.CharacterItem
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiEvent
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiState
import com.cyrilpillai.marvelverse.ui.theme.Red100

@Composable
fun CharactersRoute(
    onCharacterClicked: (characterId: Int) -> Unit,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CharactersScreen(
        state,
        viewModel::onEvent,
        onCharacterClicked
    )
}

@Composable
fun CharactersScreen(
    state: CharactersUiState,
    onEvent: (event: CharactersUiEvent) -> Unit,
    onCharacterClicked: (characterId: Int) -> Unit,
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
            is CharactersUiState.Loading -> {
                CircularProgressIndicator()
            }

            is CharactersUiState.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    verticalItemSpacing = 12.dp,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(state.characters) { CharacterView(it, onCharacterClicked) }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun CharacterView(
    characterItem: CharacterItem,
    onCharacterClicked: (characterId: Int) -> Unit,
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
            .clickable { onCharacterClicked(characterItem.id) }
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


@Preview
@Composable
fun CharactersScreenPreview() {
    CharactersScreen(
        state = CharactersUiState.Success(
            characters = listOf(
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
            )
        ),
        onEvent = {},
        onCharacterClicked = {}
    )
}