package com.mutakinngoding.disneycharacters.core.di

import android.content.Context
import androidx.room.Room
import com.mutakinngoding.disneycharacters.core.R
import com.mutakinngoding.disneycharacters.core.data.source.local.room.DisneyCharactersDao
import com.mutakinngoding.disneycharacters.core.data.source.local.room.DisneyDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) : DisneyDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(context.getString(R.string.sqlite_encrypt_key).toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            DisneyDatabase::class.java,
            "disney.db"
        )
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideDao(database: DisneyDatabase) : DisneyCharactersDao {
        return database.disneyCharactersDao()
    }
}