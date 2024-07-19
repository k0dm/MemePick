package com.bugbender.memepick.favorites

import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.favorites.adapter.ItemActions
import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.RunAsync
import com.bugbender.mempick.core.firebase.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val navigation: Navigation.Update,
    private val favoritesRepository: FavoritesRepository.GetAndRemove,
    private val authRepository: AuthRepository.UserIdAndCheck,
    private val liveDataWrapper: FavoritesLiveDataWrapper,
    private val mapper: FavoritesResultMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<FavoritesUiState>, ItemActions {

    fun checkIsUserLoggedIn() {
        if (!authRepository.isUserLogged()) {
            navigation.goToFavoritesNotLogged()
        }
    }

    fun getAllMemes() = runAsync({
        favoritesRepository.allMemes()
    }) { favoritesResult ->
        favoritesResult.map(mapper = mapper)
    }

    override fun removeMeme(postLink: String) = runAsync({
        favoritesRepository.removeMeme(postLink = postLink)
    }) {
        liveDataWrapper.removeMeme(postLink = postLink)
    }

    override fun liveData() = liveDataWrapper.liveData()
}