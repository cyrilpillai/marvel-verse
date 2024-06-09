package com.cyrilpillai.marvelverse.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cyrilpillai.marvelverse.characters.data.local.dao.CharacterDao
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import com.cyrilpillai.marvelverse.comics.data.local.dao.ComicDao
import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity
import com.cyrilpillai.marvelverse.events.data.local.dao.EventDao
import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity

@Database(
    entities = [
        CharacterEntity::class,
        ComicEntity::class,
        EventEntity::class
    ],
    version = 1
)
abstract class MarvelVerseDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun comicDao(): ComicDao
    abstract fun eventDao(): EventDao
}