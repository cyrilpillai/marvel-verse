package com.cyrilpillai.marvelverse.events.domain

import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

interface EventRepo {
    suspend fun getEventsCount(): Int
    fun getAllEvents(): Flow<List<EventEntity>>
    suspend fun fetchEvents()
}