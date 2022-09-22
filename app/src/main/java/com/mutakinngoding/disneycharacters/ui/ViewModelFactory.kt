package com.mutakinngoding.disneycharacters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mutakinngoding.disneycharacters.core.di.AppScope
import com.mutakinngoding.disneycharacters.core.domain.usecase.CharacterUseCase
import com.mutakinngoding.disneycharacters.ui.detail.DetailViewModel
import com.mutakinngoding.disneycharacters.ui.home.HomeViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val characterUseCase: CharacterUseCase) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(characterUseCase) as T
        }
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(characterUseCase) as T
        }

        throw ClassNotFoundException()
    }
}