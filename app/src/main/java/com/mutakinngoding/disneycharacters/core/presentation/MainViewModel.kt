package com.mutakinngoding.disneycharacters.core.presentation

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    characterUseCase: CharacterUseCase
) : ViewModel() {

    val listCharacter = LiveDataReactiveStreams.fromPublisher(characterUseCase.getAllCharacter())

}