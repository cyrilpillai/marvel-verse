package com.cyrilpillai.marvelverse.comics.data.remote

import com.cyrilpillai.marvelverse.comics.data.remote.model.ComicResource
import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import javax.inject.Inject

class ComicRemoteDataSource @Inject constructor(
    private val service: ComicService
) {
    suspend fun fetchComics(): NetworkResult<List<ComicResource>> {
        return service.getComics()
    }
}