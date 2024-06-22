package com.bugbender.memepick.favorites

import com.bugbender.memepick.data.favorites.api.FavoriteMeme
import com.bugbender.memepick.data.favorites.api.FavoritesResult
import javax.inject.Inject


interface FavoritesResultMapper : FavoritesResult.Mapper {

    class Base @Inject constructor(
        private val liveDataWrapper: FavoritesLiveDataWrapper,
        private val mapper: ToFavoriteUiMemeMapper
    ) : FavoritesResultMapper {

        override fun mapSuccess(memes: List<FavoriteMeme>) =
            liveDataWrapper.updateUi(FavoritesUiState.Base(memes = memes.map { it.map(mapper) }))

        override fun mapEmpty() = liveDataWrapper.updateUi(FavoritesUiState.Empty)
    }
}