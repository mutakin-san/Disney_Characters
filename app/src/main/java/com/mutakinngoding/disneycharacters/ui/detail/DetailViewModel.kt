package com.mutakinngoding.disneycharacters.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: CharacterUseCase) : ViewModel() {
    fun setFavoriteCharacter(character: Character, statusFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.setFavoriteCharacter(character, statusFavorite)
        }
    }

}