package com.mutakinngoding.disneycharacters.core.domain.repository

import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface IDisneyCharactersRepository {
    fun getAllCharacters(): Flow<Resource<List<Character>>>
    fun getFavoriteCharacters() : Flow<List<Character>>
    suspend fun setFavoriteCharacter(character: Character, state: Boolean)
}