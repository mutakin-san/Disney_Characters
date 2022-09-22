package com.mutakinngoding.disneycharacters.core.di

import android.content.Context
import androidx.room.Room
import com.mutakinngoding.disneycharacters.core.data.source.local.room.DisneyCharactersDao
import com.mutakinngoding.disneycharacters.core.data.source.local.room.DisneyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) : DisneyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DisneyDatabase::class.java,
            "disney.db"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    fun provideDao(database: DisneyDatabase) : DisneyCharactersDao {
        return database.disneyCharactersDao()
    }
}