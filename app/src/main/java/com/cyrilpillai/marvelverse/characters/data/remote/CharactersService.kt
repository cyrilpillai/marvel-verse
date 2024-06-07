package com.cyrilpillai.marvelverse.characters.data.remote

import com.cyrilpillai.marvelverse.characters.data.remote.model.CharacterResource
import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import retrofit2.http.GET

interface CharactersService {
    @GET("characters")
    suspend fun getCharacters(): NetworkResult<List<CharacterResource>>
}