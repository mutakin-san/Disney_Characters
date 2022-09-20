package com.mutakinngoding.disneycharacters.core.domain.usecase

import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CharacterInteractor @Inject constructor(private val repository: IDisneyCharactersRepository) : CharacterUseCase() {
    override fun getAllCharacter(): Flowable<Resource<List<Character>>> {
        return repository.getAllCharacters()
    }

    override fun getFavoriteCharacter(): Flowable<List<Character>> {
        return repository.getFavoriteCharacters()
    }

    override fun setFavoriteCharacter(character: Character, state: Boolean) {
        repository.setFavoriteCharacter(character, state)
    }
}