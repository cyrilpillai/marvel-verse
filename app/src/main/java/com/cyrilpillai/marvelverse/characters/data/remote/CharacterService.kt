package com.cyrilpillai.marvelverse.characters.data.remote

import com.cyrilpillai.marvelverse.characters.data.remote.model.CharacterResource
import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): NetworkResult<List<CharacterResource>>
}