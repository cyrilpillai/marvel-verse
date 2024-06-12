package com.cyrilpillai.marvelverse.comics.data

import android.util.Log
import com.cyrilpillai.marvelverse.comics.data.local.ComicLocalDataSource
import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity
import com.cyrilpillai.marvelverse.comics.data.remote.ComicRemoteDataSource
import com.cyrilpillai.marvelverse.comics.domain.ComicRepo
import com.cyrilpillai.marvelverse.core.network.adapter.onError
import com.cyrilpillai.marvelverse.core.network.adapter.onException
import com.cyrilpillai.marvelverse.core.network.adapter.onSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicRepoImpl @Inject constructor(
    private val localDataSource: ComicLocalDataSource,
    private val remoteDataSource: ComicRemoteDataSource
) : ComicRepo {
    override suspend fun getComicsCount(): Int = localDataSource.getComicsCount()

    override fun getAllComics(): Flow<List<ComicEntity>> =
        localDataSource.getAllComics()

    override suspend fun fetchComics() {
        remoteDataSource.fetchComics()
            .onSuccess { comics ->
                localDataSource.upsertAllComics(
                    comics.map {
                        ComicEntity(it)
                    }
                )
            }
            .onError { code, message ->
                Log.d(
                    "MVDebug",
                    "Error occurred while fetching comics$code $message"
                )
            }
            .onException { it.printStackTrace() }
    }
}