package com.cyrilpillai.marvelverse.events.data

import android.util.Log
import com.cyrilpillai.marvelverse.core.network.adapter.onError
import com.cyrilpillai.marvelverse.core.network.adapter.onException
import com.cyrilpillai.marvelverse.core.network.adapter.onSuccess
import com.cyrilpillai.marvelverse.events.data.local.EventLocalDataSource
import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity
import com.cyrilpillai.marvelverse.events.data.remote.EventRemoteDataSource
import com.cyrilpillai.marvelverse.events.domain.EventRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventRepoImpl @Inject constructor(
    private val localDataSource: EventLocalDataSource,
    private val remoteDataSource: EventRemoteDataSource
) : EventRepo {
    override suspend fun getEventsCount(): Int = localDataSource.getEventsCount()

    override fun getAllEvents(): Flow<List<EventEntity>> =
        localDataSource.getAllEvents()

    override suspend fun fetchEvents() {
        remoteDataSource.fetchEvents()
            .onSuccess { events ->
                localDataSource.upsertAllEvents(
                    events.map {
                        EventEntity(it)
                    }
                )
            }
            .onError { code, message ->
                Log.d(
                    "MVDebug",
                    "Error occurred while fetching events: code: $code, message: $message"
                )
            }
            .onException { it.printStackTrace() }
    }
}