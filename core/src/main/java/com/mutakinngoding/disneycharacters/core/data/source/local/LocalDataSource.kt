package com.mutakinngoding.disneycharacters.core.data.source.local

import com.mutakinngoding.disneycharacters.core.data.source.local.model.CharacterModel
import com.mutakinngoding.disneycharacters.core.data.source.local.room.DisneyCharactersDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val disneyCharactersDao: DisneyCharactersDao) {

    fun getAllCharacters(): Flow<List<CharacterModel>> {
        return disneyCharactersDao.getAllCharacters()
    }

    fun getFavoriteCharacters(): Flow<List<CharacterModel>> {
        return disneyCharactersDao.getFavoriteCharacter()
    }

    suspend fun insertCharacter(characterList: List<CharacterModel>) =
        disneyCharactersDao.insertCharacter(characterList)

    suspend fun setFavoriteCharacter(character: CharacterModel, newState: Boolean) {
        character.isFavorite = newState
        disneyCharactersDao.updateFavoriteCharacter(character)
    }

}