package com.cyrilpillai.marvelverse.characters.di

import com.cyrilpillai.marvelverse.characters.data.CharactersRepoImpl
import com.cyrilpillai.marvelverse.characters.data.remote.CharactersService
import com.cyrilpillai.marvelverse.characters.domain.CharactersRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
interface CharactersModule {
    @Binds
    fun bindCharacterRepo(impl: CharactersRepoImpl): CharactersRepo

    companion object {
        @Provides
        fun provideCharactersService(retrofit: Retrofit): CharactersService =
            retrofit.create(CharactersService::class.java)
    }
}