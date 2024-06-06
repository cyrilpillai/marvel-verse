package com.cyrilpillai.marvelverse.characters.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyrilpillai.marvelverse.characters.view.model.CharacterItem
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiEvent
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiState

@Composable
fun CharactersRoute(
    onCharacterClicked: (characterId: String) -> Unit,
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
    onCharacterClicked: (characterId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        when (state) {
            is CharactersUiState.Loading -> {
                CircularProgressIndicator()
            }

            is CharactersUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.characters) {
                        CharacterView(it, onCharacterClicked)
                    }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun CharacterView(
    characterItem: CharacterItem,
    onCharacterClicked: (characterId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = characterItem.name)
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
                    name = "Ironman"
                )
            )
        ),
        onEvent = {},
        onCharacterClicked = {},
    )
}