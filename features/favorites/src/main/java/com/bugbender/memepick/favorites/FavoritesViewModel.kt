package com.bugbender.memepick.favorites

import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.favorites.adapter.ItemActions
import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository.GetAndRemove,
    private val liveDataWrapper: FavoritesLiveDataWrapper,
    private val mapper: FavoritesResultMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<FavoritesUiState>, ItemActions {

    fun getAllMemes() = runAsync({
        repository.allMemes()
    }) { favoritesResult ->

        favoritesResult.map(mapper = mapper)
    }

    override fun removeMemeById(id: Long) = runAsync({
        repository.removeMeme(id = id)
    }) {
        //todo refresh the list in the adapter
    }

    override fun liveData() = liveDataWrapper.liveData()
}

