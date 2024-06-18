package com.bugbender.memepick.memes.presentation

import com.bugbender.memepick.presentation.LiveDataWrapper
import javax.inject.Inject
import javax.inject.Singleton

interface MemesLiveDataWrapper: LiveDataWrapper<MemesUiState> {

    class Base @Inject constructor(): MemesLiveDataWrapper, LiveDataWrapper.Base<MemesUiState>()
}