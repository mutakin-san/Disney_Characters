package com.mutakinngoding.disneycharacters.di

import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterInteractor
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(characterInteractor: CharacterInteractor) : CharacterUseCase
}