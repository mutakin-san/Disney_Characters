package com.mutakinngoding.disneycharacters.core.di

import com.mutakinngoding.disneycharacters.core.data.DisneyCharactersRepository
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(
        disneyCharactersRepository: DisneyCharactersRepository
    ): IDisneyCharactersRepository


}