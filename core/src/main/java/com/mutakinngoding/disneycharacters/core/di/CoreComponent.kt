package com.mutakinngoding.disneycharacters.core.di

import android.content.Context
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): IDisneyCharactersRepository
}