package com.mutakinngoding.disneycharacters.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import com.mutakinngoding.disneycharacters.favorite.di.FavoriteScope
import javax.inject.Inject

@FavoriteScope
class FavoriteViewModel @Inject constructor(useCase: CharacterUseCase): ViewModel() {
    val favoriteCharacters = useCase.getFavoriteCharacter().asLiveData()
}