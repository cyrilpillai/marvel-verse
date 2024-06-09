package com.cyrilpillai.marvelverse.characters.view.list.model

import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity

sealed class CharacterListUiState {
    data object Loading : CharacterListUiState()

    data class Success(
        val characters: List<CharacterItem>
    ) : CharacterListUiState()

    data class Failure(
        val errorMessage: String
    ) : CharacterListUiState()
}

data class CharacterItem(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String
) {
    constructor(characterEntity: CharacterEntity) : this(
        id = characterEntity.id,
        name = characterEntity.name,
        description = characterEntity.description,
        thumbnailUrl = characterEntity.thumbnailUrl
    )
}