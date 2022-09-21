package com.mutakinngoding.disneycharacters.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mutakinngoding.disneycharacters.core.data.source.local.model.CharacterModel
import com.mutakinngoding.disneycharacters.core.data.source.local.model.ListConverter

@Database(entities = [CharacterModel::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class DisneyDatabase : RoomDatabase() {
    abstract fun disneyCharactersDao(): DisneyCharactersDao
}