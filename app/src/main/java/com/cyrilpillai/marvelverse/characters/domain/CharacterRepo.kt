package com.cyrilpillai.marvelverse.characters.domain

import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepo {
    suspend fun getCharactersCount(): Int
    fun getAllCharacters(): Flow<List<CharacterEntity>>
    suspend fun fetchCharacters(offset: Int, limit: Int)
}