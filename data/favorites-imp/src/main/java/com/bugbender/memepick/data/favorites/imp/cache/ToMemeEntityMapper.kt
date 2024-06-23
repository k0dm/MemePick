package com.bugbender.memepick.data.favorites.imp.cache

import com.bugbender.memepick.data.favorites.api.FavoriteMemeMapper
import javax.inject.Inject

interface ToMemeEntityMapper : FavoriteMemeMapper<MemeEntity> {

    class Base @Inject constructor() : ToMemeEntityMapper {

        override fun map(
            postLink: String,
            subreddit: String,
            title: String,
            url: String,
            nsfw: Boolean,
            author: String,
            imageData: ByteArray
        ) = MemeEntity(
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