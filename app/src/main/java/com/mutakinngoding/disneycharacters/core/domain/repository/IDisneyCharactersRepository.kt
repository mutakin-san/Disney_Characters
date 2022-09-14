package com.mutakinngoding.disneycharacters.core.domain.repository

import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import io.reactivex.Flowable

interface IDisneyCharactersRepository {
    fun getAllCharacters(): Flowable<Resource<List<Character>>>
    fun getFavoriteCharacters() : Flowable<List<Character>>
    fun setFavoriteCharacter(character: Character, state: Boolean)
}