package com.bugbender.memepick.favorites.adapter

import com.bugbender.memepick.favorites.databinding.ViewholderMemeBinding

interface FavoritesUi {

    fun type(): FavoritesType

    fun show(binding: ViewholderMemeBinding) = Unit

    fun remove(viewModel: ItemActions) = Unit

    data class Meme(
        private val id: Long,
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String
    ) : FavoritesUi {

        override fun type() = FavoritesType.Meme

        override fun show(binding: ViewholderMemeBinding) = with(binding) {
            subredditTextView.text = subreddit
            titleTextView.text = title
            userTextView.text = author
            //todo logic for picture
        }

        override fun remove(viewModel: ItemActions) = viewModel.removeMemeById(id = id)
    }


    object Empty : FavoritesUi {

        override fun type() = FavoritesType.Empty
    }
}