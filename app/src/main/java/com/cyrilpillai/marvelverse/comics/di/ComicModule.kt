package com.cyrilpillai.marvelverse.comics.di

import com.cyrilpillai.marvelverse.comics.data.ComicRepoImpl
import com.cyrilpillai.marvelverse.comics.data.local.dao.ComicDao
import com.cyrilpillai.marvelverse.comics.data.remote.ComicService
import com.cyrilpillai.marvelverse.comics.domain.ComicRepo
import com.cyrilpillai.marvelverse.core.database.MarvelVerseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
interface ComicModule {
    @Binds
    fun bindComicRepo(impl: ComicRepoImpl): ComicRepo

    companion object {
        @Provides
        fun provideComicService(retrofit: Retrofit): ComicService =
            retrofit.create(ComicService::class.java)

        @Provides
        fun provideComicDao(appDatabase: MarvelVerseDatabase): ComicDao {
            return appDatabase.comicDao()
        }
    }
}