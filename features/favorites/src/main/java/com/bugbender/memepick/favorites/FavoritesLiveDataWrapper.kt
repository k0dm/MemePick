package com.bugbender.memepick.favorites

import com.bugbender.memepick.presentation.LiveDataWrapper
import javax.inject.Inject

interface FavoritesLiveDataWrapper : LiveDataWrapper<FavoritesUiState> {

    class Base @Inject constructor() : FavoritesLiveDataWrapper,
        LiveDataWrapper.Base<FavoritesUiState>()
}