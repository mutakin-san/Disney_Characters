package com.mutakinngoding.disneycharacters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: CharacterUseCase) : ViewModel() {

    val listCharacter =  useCase.getAllCharacter().asLiveData()
}