package com.cyrilpillai.marvelverse.events.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Upsert
    suspend fun upsertAllEvents(events: List<EventEntity>)

    @Query("SELECT COUNT(id) FROM events")
    suspend fun getEventCount(): Int

    @Transaction
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<EventEntity>>
}