package com.mutakinngoding.disneycharacters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(useCase: CharacterUseCase) : ViewModel() {

    val listCharacter =  useCase.getAllCharacter().asLiveData()
}