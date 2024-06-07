package com.cyrilpillai.marvelverse.characters.view.model

import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity

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
    val name: String,
    val description: String
) {
    constructor(characterEntity: CharacterEntity) : this(
        id = characterEntity.id,
        name = characterEntity.name,
        description = characterEntity.description
    )
}