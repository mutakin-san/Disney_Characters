package com.mutakinngoding.disneycharacters.core.domain.usecase

import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import kotlinx.coroutines.flow.Flow

abstract class CharacterUseCase {
    abstract fun getAllCharacter(): Flow<Resource<List<Character>>>
    abstract fun getFavoriteCharacter(): Flow<List<Character>>
    abstract suspend fun setFavoriteCharacter(character: Character, state: Boolean)
}