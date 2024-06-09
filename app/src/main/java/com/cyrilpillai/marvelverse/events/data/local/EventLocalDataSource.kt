package com.cyrilpillai.marvelverse.events.data.local

import com.cyrilpillai.marvelverse.events.data.local.dao.EventDao
import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventLocalDataSource @Inject constructor(
    private val eventDao: EventDao
) {
    suspend fun insertAllEvents(events: List<EventEntity>) {
        eventDao.insertAllEvents(events)
    }

    suspend fun getEventsCount(): Int = eventDao.getEventsCount()

    fun getAllEvents(): Flow<List<EventEntity>> = eventDao.getAllEvents()
}