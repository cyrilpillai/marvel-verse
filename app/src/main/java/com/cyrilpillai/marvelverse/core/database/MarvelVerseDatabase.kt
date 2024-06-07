package com.cyrilpillai.marvelverse.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cyrilpillai.marvelverse.characters.data.local.dao.CharacterDao
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelVerseDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}