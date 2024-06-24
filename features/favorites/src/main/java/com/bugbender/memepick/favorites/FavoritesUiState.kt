package com.bugbender.memepick.favorites

import com.bugbender.memepick.favorites.adapter.FavoritesUi
import com.bugbender.memepick.favorites.adapter.UpdateFavoritesAdapter

interface FavoritesUiState {

    fun show(adapter: UpdateFavoritesAdapter)

    fun removeMeme(postLink: String): FavoritesUiState = this

    data class Base(private val memes: List<FavoritesUi>) : FavoritesUiState {

        override fun show(adapter: UpdateFavoritesAdapter) = adapter.update(memes)

        override fun removeMeme(postLink: String): FavoritesUiState =
            memes
                .filter {
                    it.hasNotPostLink(postLink = postLink)
                }
                .let {
                    if (it.isNotEmpty()) Base(memes = it) else Empty
                }
    }

    object Empty : FavoritesUiState {

        override fun show(adapter: UpdateFavoritesAdapter) =
            adapter.update(listOf(FavoritesUi.Empty))
    }
}

