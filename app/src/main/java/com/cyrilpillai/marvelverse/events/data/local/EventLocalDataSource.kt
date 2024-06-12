package com.cyrilpillai.marvelverse.events.data.local

import com.cyrilpillai.marvelverse.events.data.local.dao.EventDao
import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventLocalDataSource @Inject constructor(
    private val eventDao: EventDao
) {
    suspend fun upsertAllEvents(events: List<EventEntity>) {
        eventDao.upsertAllEvents(events)
    }

    suspend fun getEventsCount(): Int = eventDao.getEventCount()

    fun getAllEvents(): Flow<List<EventEntity>> = eventDao.getAllEvents()
}