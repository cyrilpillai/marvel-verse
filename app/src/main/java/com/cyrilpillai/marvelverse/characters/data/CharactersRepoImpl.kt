package com.cyrilpillai.marvelverse.characters.data

import com.cyrilpillai.marvelverse.characters.domain.CharactersRepo
import com.cyrilpillai.marvelverse.characters.view.model.CharacterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepoImpl @Inject constructor() : CharactersRepo {
    override fun fetchCharacters(): Flow<List<CharacterItem>> {
        return flow {
            emit(
                listOf(
                    CharacterItem(id = 101, name = "Ironman"),
                    CharacterItem(id = 102, name = "Spiderman"),
                    CharacterItem(id = 103, name = "Thor"),
                    CharacterItem(id = 104, name = "Captain America"),
                    CharacterItem(id = 105, name = "Hawkeye"),
                    CharacterItem(id = 106, name = "Black Widow"),
                    CharacterItem(id = 107, name = "Hulk"),
                    CharacterItem(id = 108, name = "Nick Fury")
                )
            )
        }
    }
}