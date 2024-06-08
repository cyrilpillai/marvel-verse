package com.cyrilpillai.marvelverse.comics.data.local

import com.cyrilpillai.marvelverse.comics.data.local.dao.ComicDao
import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicLocalDataSource @Inject constructor(
    private val comicDao: ComicDao
) {
    suspend fun insertAllComics(characters: List<ComicEntity>) {
        comicDao.insertAllComics(characters)
    }

    suspend fun getComicsCount(): Int = comicDao.getComicsCount()

    fun getAllComics(): Flow<List<ComicEntity>> = comicDao.getAllComics()
}