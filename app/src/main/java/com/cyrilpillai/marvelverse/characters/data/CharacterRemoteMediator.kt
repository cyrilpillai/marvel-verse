package com.cyrilpillai.marvelverse.characters.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.cyrilpillai.marvelverse.characters.data.local.CharacterLocalDataSource
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import com.cyrilpillai.marvelverse.characters.data.remote.CharacterRemoteDataSource
import com.cyrilpillai.marvelverse.core.DATA_LIMIT
import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource,
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    0
                } else {
                    localDataSource.getCharactersCount()
                }
            }
        }

        Log.d("MVDebug", "offset = $offset")

        return when (
            val result = remoteDataSource.fetchCharacters(
                offset = offset,
                limit = DATA_LIMIT
            )
        ) {
            is NetworkResult.Error -> MediatorResult.Error(Throwable(result.message))
            is NetworkResult.Exception -> MediatorResult.Error(result.throwable)
            is NetworkResult.Success -> {
                localDataSource.upsertAllCharacters(
                    result.data.map {
                        CharacterEntity(it)
                    }
                )
                MediatorResult.Success(endOfPaginationReached = result.data.isEmpty())
            }
        }
    }
}