package com.cyrilpillai.marvelverse.comics.data.remote

import com.cyrilpillai.marvelverse.comics.data.remote.model.ComicResource
import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import retrofit2.http.GET

interface ComicService {
    @GET("comics")
    suspend fun getComics(): NetworkResult<List<ComicResource>>
}