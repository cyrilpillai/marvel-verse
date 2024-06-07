package com.cyrilpillai.marvelverse.characters.data

import android.util.Log
import com.cyrilpillai.marvelverse.characters.data.remote.CharactersRemoteDataSource
import com.cyrilpillai.marvelverse.characters.domain.CharactersRepo
import com.cyrilpillai.marvelverse.characters.view.model.CharacterItem
import com.cyrilpillai.marvelverse.core.network.adapter.onError
import com.cyrilpillai.marvelverse.core.network.adapter.onException
import com.cyrilpillai.marvelverse.core.network.adapter.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepoImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepo {
    override suspend fun fetchCharacters(): Flow<List<CharacterItem>> {
        return flow {
            remoteDataSource.fetchCharacters()
                .onSuccess { response ->
                    emit(
                        response.map {
                            CharacterItem(
                                id = it.id,
                                name = it.name,
                                description = it.description
                            )
                        })
                }
                .onError { code, message -> Log.d("MVDebug", "Error occurred: $code, $message") }
                .onException { it.printStackTrace() }
        }
    }
}