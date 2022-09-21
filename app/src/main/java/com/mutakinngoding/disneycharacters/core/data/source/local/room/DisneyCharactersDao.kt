package com.mutakinngoding.disneycharacters.core.data.source.local.room

import androidx.room.*
import com.mutakinngoding.disneycharacters.core.data.source.local.model.CharacterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DisneyCharactersDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterModel>>

    @Query("SELECT * FROM characters where isFavorite = 1")
    fun getFavoriteCharacter(): Flow<List<CharacterModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characters: List<CharacterModel>)

    @Update
    suspend fun updateFavoriteCharacter(character: CharacterModel)
}