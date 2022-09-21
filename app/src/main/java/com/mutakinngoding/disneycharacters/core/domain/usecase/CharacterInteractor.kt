package com.mutakinngoding.disneycharacters.core.domain.usecase

import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterInteractor @Inject constructor(private val repository: IDisneyCharactersRepository) : CharacterUseCase() {
    override fun getAllCharacter(): Flow<Resource<List<Character>>> {
        return repository.getAllCharacters()
    }

    override fun getFavoriteCharacter(): Flow<List<Character>> {
        return repository.getFavoriteCharacters()
    }

    override suspend fun setFavoriteCharacter(character: Character, state: Boolean) {
        repository.setFavoriteCharacter(character, state)
    }
}