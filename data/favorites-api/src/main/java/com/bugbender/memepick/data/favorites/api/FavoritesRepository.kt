package com.bugbender.memepick.data.favorites.api

interface FavoritesRepository {

    interface Add {
        suspend fun addMeme(userId: String, meme: FavoriteMeme)
    }

    interface Remove {
        suspend fun removeMeme(postLink: String)
    }

    interface Get {
        suspend fun allMemes(): FavoritesResult
    }

    interface AddAndRemove : Add, Remove

    interface GetAndRemove : Get, Remove

    interface All : AddAndRemove, GetAndRemove
}