package com.cyrilpillai.marvelverse.characters.data

import android.util.Log
import com.cyrilpillai.marvelverse.characters.data.local.CharacterLocalDataSource
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import com.cyrilpillai.marvelverse.characters.data.remote.CharacterRemoteDataSource
import com.cyrilpillai.marvelverse.characters.domain.CharacterRepo
import com.cyrilpillai.marvelverse.core.network.adapter.onError
import com.cyrilpillai.marvelverse.core.network.adapter.onException
import com.cyrilpillai.marvelverse.core.network.adapter.onSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepo {
    override suspend fun getCharactersCount(): Int = localDataSource.getCharactersCount()

    override fun getAllCharacters(): Flow<List<CharacterEntity>> =
        localDataSource.getAllCharacters()

    override suspend fun fetchCharacters() {
        remoteDataSource.fetchCharacters()
            .onSuccess { characters ->
                localDataSource.insertAllCharacters(
                    characters.map {
                        CharacterEntity(it)
                    }
                )
            }
            .onError { code, message ->
                Log.d(
                    "MVDebug",
                    "Error occurred while fetching characters: code: $code, message: $message"
                )
            }
            .onException { it.printStackTrace() }
    }
}