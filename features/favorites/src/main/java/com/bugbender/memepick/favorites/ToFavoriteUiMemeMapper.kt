package com.bugbender.memepick.favorites

import android.graphics.BitmapFactory
import com.bugbender.memepick.data.favorites.api.FavoriteMemeMapper
import com.bugbender.memepick.favorites.adapter.FavoritesUi
import javax.inject.Inject

interface ToFavoriteUiMemeMapper : FavoriteMemeMapper<FavoritesUi> {

    class Base @Inject constructor() : ToFavoriteUiMemeMapper {

        override fun map(
            postLink: String,
            subreddit: String,
            title: String,
            url: String,
            nsfw: Boolean,
            author: String,
            imageData: ByteArray
        ) = FavoritesUi.Meme(
            postLink = postLink,
            subreddit = subreddit,
            title = title,
            url = url,
            nsfw = nsfw,
            author = author,
            imageData = imageData
        )
    }
}