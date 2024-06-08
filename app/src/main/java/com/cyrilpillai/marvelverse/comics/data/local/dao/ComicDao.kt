package com.cyrilpillai.marvelverse.comics.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comic: ComicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllComics(comics: List<ComicEntity>)

    @Query("SELECT COUNT(id) FROM comics")
    suspend fun getComicsCount(): Int

    @Transaction
    @Query("SELECT * FROM comics")
    fun getAllComics(): Flow<List<ComicEntity>>
}