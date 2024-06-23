package com.bugbender.memepick.memes.presentation

import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.presentation.LiveDataWrapper
import javax.inject.Inject
import javax.inject.Singleton

interface MemesLiveDataWrapper : LiveDataWrapper<MemesUiState> {

    fun changeFavorite(): MemesUiState

    class Base @Inject constructor() : MemesLiveDataWrapper, LiveDataWrapper.Base<MemesUiState>() {

        override fun changeFavorite(): MemesUiState {
            val currentState: MemesUiState = liveData.value!!
            liveData.value = MemesUiState.Progress
            return currentState
        }
    }
}