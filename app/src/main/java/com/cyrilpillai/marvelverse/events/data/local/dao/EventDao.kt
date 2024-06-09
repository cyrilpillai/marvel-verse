package com.cyrilpillai.marvelverse.events.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvents(events: List<EventEntity>)

    @Query("SELECT COUNT(id) FROM events")
    suspend fun getEventsCount(): Int

    @Transaction
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<EventEntity>>
}