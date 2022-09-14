package com.mutakinngoding.disneycharacters.core.data.source.local.room

import androidx.room.*
import com.mutakinngoding.disneycharacters.core.data.source.local.model.CharacterModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface DisneyCharactersDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flowable<List<CharacterModel>>


    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Flowable<CharacterModel>

    @Query("SELECT * FROM characters where isFavorite = 1")
    fun getFavoriteCharacter(): Flowable<List<CharacterModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characters: List<CharacterModel>): Completable

    @Update
    fun updateFavoriteCharacter(character: CharacterModel)
}