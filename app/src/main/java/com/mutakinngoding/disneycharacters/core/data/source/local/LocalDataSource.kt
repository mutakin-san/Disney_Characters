package com.mutakinngoding.disneycharacters.core.data.source.local

import com.mutakinngoding.disneycharacters.core.data.source.local.model.CharacterModel
import com.mutakinngoding.disneycharacters.core.data.source.local.room.DisneyCharactersDao
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val disneyCharactersDao: DisneyCharactersDao) {

    fun getAllCharacters(): Flowable<List<CharacterModel>> {
        return disneyCharactersDao.getAllCharacters()
    }

    fun getFavoriteCharacters(): Flowable<List<CharacterModel>> {
        return disneyCharactersDao.getFavoriteCharacter()
    }

    fun insertCharacter(characterList: List<CharacterModel>) =
        disneyCharactersDao.insertCharacter(characterList)

    fun setFavoriteCharacter(character: CharacterModel, newState: Boolean): Completable {
        character.isFavorite = newState
        return disneyCharactersDao.updateFavoriteCharacter(character)
    }

}