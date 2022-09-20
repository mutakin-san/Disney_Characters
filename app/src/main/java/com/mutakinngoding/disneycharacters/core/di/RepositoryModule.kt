package com.mutakinngoding.disneycharacters.core.di

import com.mutakinngoding.disneycharacters.core.data.DisneyCharactersRepository
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(
        disneyCharactersRepository: DisneyCharactersRepository
    ): IDisneyCharactersRepository


}