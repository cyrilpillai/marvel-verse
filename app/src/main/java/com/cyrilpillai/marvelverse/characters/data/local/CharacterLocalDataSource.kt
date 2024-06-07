package com.cyrilpillai.marvelverse.characters.data.local

import com.cyrilpillai.marvelverse.characters.data.local.dao.CharacterDao
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend fun insertAllCharacters(characters: List<CharacterEntity>) {
        characterDao.insertAllCharacters(characters)
    }

    suspend fun getCharactersCount(): Int = characterDao.getCharactersCount()

    fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAllCharacters()
}