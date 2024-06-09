package com.cyrilpillai.marvelverse.events.data.remote

import com.cyrilpillai.marvelverse.core.network.adapter.model.NetworkResult
import com.cyrilpillai.marvelverse.events.data.remote.model.EventResource
import javax.inject.Inject

class EventRemoteDataSource @Inject constructor(
    private val service: EventService
) {
    suspend fun fetchEvents(): NetworkResult<List<EventResource>> {
        return service.getEvents()
    }
}