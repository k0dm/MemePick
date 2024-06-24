package com.bugbender.memepick.favorites

import com.bugbender.memepick.presentation.LiveDataWrapper
import javax.inject.Inject

interface FavoritesLiveDataWrapper : LiveDataWrapper<FavoritesUiState> {

    fun removeMeme(postLink: String)

    class Base @Inject constructor() : FavoritesLiveDataWrapper,
        LiveDataWrapper.Base<FavoritesUiState>() {

        override fun removeMeme(postLink: String) {
            val actualUiState = liveData.value!!
            updateUi(value = actualUiState.removeMeme(postLink))
        }
    }
}