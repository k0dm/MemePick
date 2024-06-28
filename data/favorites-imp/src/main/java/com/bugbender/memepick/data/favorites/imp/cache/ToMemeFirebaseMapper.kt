package com.bugbender.memepick.data.favorites.imp.cache

import com.bugbender.memepick.data.favorites.api.FavoriteMemeMapper
import javax.inject.Inject

interface ToMemeFirebaseMapper : FavoriteMemeMapper<HashMap<String, Any>> {

    class Base @Inject constructor() : ToMemeFirebaseMapper {

        override fun map(
            postLink: String,
            subreddit: String,
            title: String,
            url: String,
            nsfw: Boolean,
            author: String,
            imageData: ByteArray
        ) = hashMapOf<String, Any>(
            "postLink" to postLink,
            "subreddit" to subreddit,
            "title" to title,
            "url" to url,
            "nsfw" to nsfw,
            "author" to author,
        )
    }
}