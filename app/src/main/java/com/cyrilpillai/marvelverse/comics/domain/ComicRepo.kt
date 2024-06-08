package com.cyrilpillai.marvelverse.comics.domain

import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity
import kotlinx.coroutines.flow.Flow

interface ComicRepo {
    suspend fun getComicsCount(): Int
    fun getAllComics(): Flow<List<ComicEntity>>
    suspend fun fetchComics()
}