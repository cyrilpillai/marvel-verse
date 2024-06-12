package com.cyrilpillai.marvelverse.characters.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Upsert
    suspend fun upsertAllCharacters(characters: List<CharacterEntity>)

    @Query("SELECT COUNT(id) FROM characters")
    suspend fun getCharactersCount(): Int

    @Transaction
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters(): Int
}