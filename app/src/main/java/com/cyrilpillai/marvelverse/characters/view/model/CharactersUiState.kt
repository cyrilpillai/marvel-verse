package com.cyrilpillai.marvelverse.characters.view.model

sealed class CharactersUiState {
    data object Loading : CharactersUiState()

    data class Success(
        val characters: List<CharacterItem>
    ) : CharactersUiState()

    data class Failure(
        val errorMessage: String
    ) : CharactersUiState()
}

data class CharacterItem(
    val id: Int,
    val name: String
)