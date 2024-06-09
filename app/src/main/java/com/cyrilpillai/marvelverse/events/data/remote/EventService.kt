package com.cyrilpillai.marvelverse.events.data.remote

import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import com.cyrilpillai.marvelverse.events.data.remote.model.EventResource
import retrofit2.http.GET

interface EventService {
    @GET("events")
    suspend fun getEvents(): NetworkResult<List<EventResource>>
}