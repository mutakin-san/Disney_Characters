package com.mutakinngoding.disneycharacters.di

import com.mutakinngoding.disneycharacters.MainActivity
import com.mutakinngoding.disneycharacters.core.di.CoreComponent
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class, ViewModelModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }


    fun inject(activity: MainActivity)

}