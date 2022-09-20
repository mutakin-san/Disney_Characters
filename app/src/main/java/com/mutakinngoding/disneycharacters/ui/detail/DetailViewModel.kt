package com.mutakinngoding.disneycharacters.ui.detail

import androidx.lifecycle.ViewModel
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: CharacterUseCase) : ViewModel() {
    fun setFavoriteCharacter(character: Character, statusFavorite: Boolean) {
        useCase.setFavoriteCharacter(character, statusFavorite)
    }

}