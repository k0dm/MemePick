package com.bugbender.memepick.data.favorites.api

interface FavoritesRepository {

    interface Add {
        suspend fun addMeme(meme: FavoriteMeme)
    }

    interface Remove {
        suspend fun removeMeme(id: Long)
    }

    interface Get {
        suspend fun allMemes(): FavoritesResult
    }

    interface AddAndRemove : Add, Remove

    interface GetAndRemove : Get, Remove

    interface All : AddAndRemove, GetAndRemove
}