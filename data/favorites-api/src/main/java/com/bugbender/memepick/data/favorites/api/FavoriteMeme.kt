package com.bugbender.memepick.data.favorites.api

data class FavoriteMeme(
    private val postLink: String,
    private val subreddit: String,
    private val title: String,
    private val url: String,
    private val nsfw: Boolean,
    private val author: String,
    private val imageData: ByteArray
) {

    fun <T> map(mapper: FavoriteMemeMapper<T>): T =
        mapper.map(postLink, subreddit, title, url, nsfw, author, imageData)
}