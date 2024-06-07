package com.cyrilpillai.marvelverse.core.database.di

import android.content.Context
import androidx.room.Room
import com.cyrilpillai.marvelverse.core.database.MarvelVerseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "marvel-verse-db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): MarvelVerseDatabase = Room.databaseBuilder(
        applicationContext,
        MarvelVerseDatabase::class.java, DATABASE_NAME
    ).build()
}