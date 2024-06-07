package com.cyrilpillai.marvelverse.characters.di

import com.cyrilpillai.marvelverse.characters.data.CharacterRepoImpl
import com.cyrilpillai.marvelverse.characters.data.local.dao.CharacterDao
import com.cyrilpillai.marvelverse.characters.data.remote.CharacterService
import com.cyrilpillai.marvelverse.characters.domain.CharacterRepo
import com.cyrilpillai.marvelverse.core.database.MarvelVerseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
interface CharacterModule {
    @Binds
    fun bindCharacterRepo(impl: CharacterRepoImpl): CharacterRepo

    companion object {
        @Provides
        fun provideCharactersService(retrofit: Retrofit): CharacterService =
            retrofit.create(CharacterService::class.java)

        @Provides
        fun provideCharacterDao(appDatabase: MarvelVerseDatabase): CharacterDao {
            return appDatabase.charactersDao()
        }
    }
}