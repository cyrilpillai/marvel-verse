package com.cyrilpillai.marvelverse.characters.di

import com.cyrilpillai.marvelverse.characters.data.CharactersRepoImpl
import com.cyrilpillai.marvelverse.characters.domain.CharactersRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CharactersModule {
    @Binds
    fun bindCharacterRepo(impl: CharactersRepoImpl): CharactersRepo
}