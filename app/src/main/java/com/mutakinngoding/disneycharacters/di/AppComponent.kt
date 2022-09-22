package com.mutakinngoding.disneycharacters.di

import com.mutakinngoding.disneycharacters.core.di.AppScope
import com.mutakinngoding.disneycharacters.core.di.CoreComponent
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import com.mutakinngoding.disneycharacters.ui.detail.DetailActivity
import com.mutakinngoding.disneycharacters.ui.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun provideUseCase() : CharacterUseCase
    fun inject(fragment: HomeFragment)
    fun inject(activity: DetailActivity)
}