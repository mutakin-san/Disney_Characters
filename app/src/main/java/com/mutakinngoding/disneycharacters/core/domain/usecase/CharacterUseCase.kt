package com.mutakinngoding.disneycharacters.core.domain.usecase

import com.mutakinngoding.disneycharacters.core.data.Resource
import io.reactivex.Flowable
import com.mutakinngoding.disneycharacters.core.domain.entity.Character

abstract class CharacterUseCase {
    abstract fun getAllCharacter(): Flowable<Resource<List<Character>>>
    abstract fun getFavoriteCharacter(): Flowable<List<Character>>
    abstract fun setFavoriteCharacter(character: Character, state: Boolean)
}