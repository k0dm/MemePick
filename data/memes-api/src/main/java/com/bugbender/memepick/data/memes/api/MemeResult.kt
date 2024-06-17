package com.bugbender.memepick.data.memes.api

interface MemeResult {

    interface Mapper {

        fun mapSuccess(
            postLink: String,
            subreddit: String,
            title: String,
            url: String,
            nsfw: Boolean,
            author: String
        )

        fun mapError(message: String)
    }

    fun map(mapper: Mapper)

    data class Success(
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String,
    ) : MemeResult {

        override fun map(mapper: Mapper) {
            mapper.mapSuccess(postLink, subreddit, title, url, nsfw, author)
        }
    }

    data class Error(private val message: String) : MemeResult {

        override fun map(mapper: Mapper) {
            mapper.mapError(message)
        }
    }
}