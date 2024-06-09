package com.cyrilpillai.marvelverse.events.di

import com.cyrilpillai.marvelverse.core.database.MarvelVerseDatabase
import com.cyrilpillai.marvelverse.events.data.EventRepoImpl
import com.cyrilpillai.marvelverse.events.data.local.dao.EventDao
import com.cyrilpillai.marvelverse.events.data.remote.EventService
import com.cyrilpillai.marvelverse.events.domain.EventRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
interface EventModule {
    @Binds
    fun bindEventRepo(impl: EventRepoImpl): EventRepo

    companion object {
        @Provides
        fun provideEventService(retrofit: Retrofit): EventService =
            retrofit.create(EventService::class.java)

        @Provides
        fun provideEventDao(appDatabase: MarvelVerseDatabase): EventDao {
            return appDatabase.eventDao()
        }
    }
}