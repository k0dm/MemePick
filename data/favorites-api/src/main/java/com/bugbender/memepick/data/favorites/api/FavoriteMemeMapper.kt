package com.bugbender.memepick.data.favorites.api

interface FavoriteMemeMapper<T> {

    fun map(
        postLink: String,
        subreddit: String,
        title: String,
        url: String,
        nsfw: Boolean,
        author: String,
        imageData: ByteArray
    ): T
}