package com.bugbender.memepick.memes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugbender.memepick.data.memes.api.MemesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemesViewModel @Inject constructor(
    private val repository: MemesRepository
) : ViewModel() {

    fun getMeme() {

        viewModelScope.launch(Dispatchers.IO) {
            repository.randomMeme()
        }
    }
}