package com.mutakinngoding.disneycharacters.favorite.di

import com.mutakinngoding.disneycharacters.di.AppComponent
import com.mutakinngoding.disneycharacters.favorite.FavoriteFragment
import dagger.Component

@FavoriteScope
@Component(dependencies = [AppComponent::class])
interface FavoriteComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)
}