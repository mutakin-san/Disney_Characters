package com.mutakinngoding.disneycharacters.ui.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(useCase: CharacterUseCase): ViewModel() {
    val favoriteCharacters = LiveDataReactiveStreams.fromPublisher(useCase.getFavoriteCharacter())
}