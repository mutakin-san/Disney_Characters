package com.mutakinngoding.disneycharacters.di

import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterInteractor
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideUseCase(characterInteractor: CharacterInteractor) : CharacterUseCase
}