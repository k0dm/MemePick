package com.bugbender.memepick.main

import androidx.lifecycle.ViewModel
import com.bugbender.memepick.memes.presentation.MemesScreen

import com.bugbender.memepick.presentation.Navigation
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable
) : ViewModel(), ProvideLiveData<Screen> {

    fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            navigation.updateUi(MemesScreen)
        }
    }

    override fun liveData() = navigation.liveData()
}