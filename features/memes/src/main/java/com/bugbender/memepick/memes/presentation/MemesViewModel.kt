package com.bugbender.memepick.memes.presentation

import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.data.memes.api.MemesRepository
import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.RunAsync
import com.bugbender.mempick.core.firebase.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemesViewModel @Inject constructor(
    private val memesRepository: MemesRepository,
    private val authRepository: AuthRepository.UserIdAndCheck,
    private val favoritesRepository: FavoritesRepository.AddAndRemove,
    private val liveDataWrapper: MemesLiveDataWrapper,
    private val mapper: MemeResult.Mapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<MemesUiState> {

    private var isUserLogged = false
    private var userId = ""

    fun checkIsUserLoggedIn() {
        isUserLogged = if (authRepository.isUserLogged()) {
            userId = authRepository.userId()
            true
        } else {
            false
        }
    }

    fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            getMeme()
        }
    }

    fun getMeme() {
        liveDataWrapper.updateUi(MemesUiState.Progress)
        runAsync({
            memesRepository.randomMeme()
        }) { memeResult ->
            memeResult.map(mapper)
        }
    }

    fun changeFavorite() {
        val uiState = liveDataWrapper.changeFavorite()
        runAsync({
            uiState.changeFavorite(favoritesRepository)
        }) { uiState ->
            liveDataWrapper.updateUi(uiState)
        }
    }

    override fun liveData() = liveDataWrapper.liveData()
}