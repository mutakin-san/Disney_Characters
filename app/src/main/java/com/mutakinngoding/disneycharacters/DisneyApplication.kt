package com.mutakinngoding.disneycharacters

import androidx.multidex.MultiDexApplication
import com.mutakinngoding.disneycharacters.core.di.CoreComponent
import com.mutakinngoding.disneycharacters.core.di.DaggerCoreComponent
import com.mutakinngoding.disneycharacters.di.AppComponent
import com.mutakinngoding.disneycharacters.di.DaggerAppComponent

open class DisneyApplication : MultiDexApplication() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}