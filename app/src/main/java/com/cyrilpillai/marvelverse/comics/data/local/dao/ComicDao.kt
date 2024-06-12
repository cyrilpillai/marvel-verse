package com.cyrilpillai.marvelverse.comics.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {
    @Upsert
    suspend fun upsertAllComics(comics: List<ComicEntity>)

    @Query("SELECT COUNT(id) FROM comics")
    suspend fun getComicsCount(): Int

    @Transaction
    @Query("SELECT * FROM comics")
    fun getAllComics(): Flow<List<ComicEntity>>
}