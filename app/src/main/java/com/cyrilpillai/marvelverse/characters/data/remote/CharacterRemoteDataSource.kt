package com.cyrilpillai.marvelverse.characters.data.remote

import com.cyrilpillai.marvelverse.characters.data.remote.model.CharacterResource
import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val service: CharacterService
) {
    suspend fun fetchCharacters(): NetworkResult<List<CharacterResource>> {
        return service.getCharacters()
    }
}