package com.bugbender.memepick.memes.presentation

import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.data.memes.api.MemesRepository
import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MemesViewModel @Inject constructor(
    private val memesRepository: MemesRepository,
    private val liveDataWrapper: MemesLiveDataWrapper,
    private val mapper: MemeResult.Mapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<MemesUiState> {

    fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            getMeme()
        }
    }

    fun getMeme() {
        liveDataWrapper.updateUi(MemesUiState.Progress)
        runAsync({
            delay(10000)
            memesRepository.randomMeme()
        }) { memeResult ->
            memeResult.map(mapper)
        }
    }

    override fun liveData() = liveDataWrapper.liveData()
}