package com.bugbender.memepick.favorites

import com.bugbender.memepick.data.favorites.api.FavoriteMemeMapper
import com.bugbender.memepick.favorites.adapter.FavoritesUi
import javax.inject.Inject

interface ToFavoriteUiMemeMapper : FavoriteMemeMapper<FavoritesUi> {

    class Base @Inject constructor() : ToFavoriteUiMemeMapper {

        override fun map(
            id: Long,
            postLink: String,
            subreddit: String,
            title: String,
            url: String,
            nsfw: Boolean,
            author: String
        ) = FavoritesUi.Meme(
            id = id,
            postLink = postLink,
            subreddit = subreddit,
            title = title,
            url = url,
            nsfw = nsfw,
            author = author
        )
    }
}