package com.mutakinngoding.disneycharacters.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: CharacterUseCase) : ViewModel() {

    val listCharacter =  LiveDataReactiveStreams.fromPublisher(useCase.getAllCharacter())
}