package com.mutakinngoding.disneycharacters.core.presentation

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import com.mutakinngoding.disneycharacters.di.AppScope
import javax.inject.Inject

class MainViewModel @Inject constructor(
    characterUseCase: CharacterUseCase
) : ViewModel() {

    val listCharacter = LiveDataReactiveStreams.fromPublisher(characterUseCase.getAllCharacter())

}