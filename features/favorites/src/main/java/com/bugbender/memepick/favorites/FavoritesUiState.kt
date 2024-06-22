package com.bugbender.memepick.favorites

import com.bugbender.memepick.favorites.adapter.FavoritesUi
import com.bugbender.memepick.favorites.adapter.UpdateFavoritesAdapter

interface FavoritesUiState {

    fun show(adapter: UpdateFavoritesAdapter)

    data class Base(private val memes: List<FavoritesUi>) : FavoritesUiState {

        override fun show(adapter: UpdateFavoritesAdapter) = adapter.update(memes)
    }

    object Empty : FavoritesUiState {

        override fun show(adapter: UpdateFavoritesAdapter) =
            adapter.update(listOf(FavoritesUi.Empty))
    }
}

