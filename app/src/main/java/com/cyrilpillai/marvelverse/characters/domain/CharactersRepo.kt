package com.cyrilpillai.marvelverse.characters.domain

import com.cyrilpillai.marvelverse.characters.view.model.CharacterItem
import kotlinx.coroutines.flow.Flow

interface CharactersRepo {
    fun fetchCharacters(): Flow<List<CharacterItem>>
}