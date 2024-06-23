package com.bugbender.memepick.favorites.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bugbender.memepick.favorites.databinding.ViewholderMemeBinding
import com.bumptech.glide.Glide
import pl.droidsonroids.gif.GifDrawable
import java.io.ByteArrayInputStream

interface FavoritesUi {

    fun type(): FavoritesType

    fun show(binding: ViewholderMemeBinding) = Unit

    fun remove(viewModel: ItemActions) = Unit

    data class Meme(
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String,
        private val imageData: ByteArray
    ) : FavoritesUi {

        override fun type() = FavoritesType.Meme

        override fun
                show(binding: ViewholderMemeBinding) = with(binding) {
            subredditTextView.text = subreddit
            titleTextView.text = title
            userTextView.text = author
            Glide.with(memeFavoriteImageView.context)
                .load(imageData)
                .into(memeFavoriteImageView)
            Unit
        }

        override fun remove(viewModel: ItemActions) = viewModel.removeMeme(postLink)
    }


    object Empty : FavoritesUi {

        override fun type() = FavoritesType.Empty
    }
}