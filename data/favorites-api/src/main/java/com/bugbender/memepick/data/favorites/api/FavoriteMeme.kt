package com.bugbender.memepick.data.favorites.api

data class FavoriteMeme(
    private val id: Long,
    private val postLink: String,
    private val subreddit: String,
    private val title: String,
    private val url: String,
    private val nsfw: Boolean,
    private val author: String,
) {

    fun <T> map(mapper: FavoriteMemeMapper<T>): T =
        mapper.map(id, postLink, subreddit, title, url, nsfw, author)
}