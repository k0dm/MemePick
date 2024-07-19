package com.bugbender.memepick.data.favorites.imp

import com.bugbender.memepick.data.favorites.api.FavoriteMeme
import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.data.favorites.api.FavoritesResult
import com.bugbender.memepick.data.favorites.imp.cache.FavoritesCacheDataSource
import com.bugbender.memepick.data.favorites.imp.cache.ToMemeEntityMapper
import com.bugbender.memepick.data.favorites.imp.cache.ToMemeFirebaseMapper
import com.bugbender.memepick.data.favorites.imp.cloud.FavoritesCloudDataSource
import javax.inject.Inject

class BaseFavoriteRepository @Inject constructor(
    private val cacheDataSource: FavoritesCacheDataSource,
    private val cloudDataSource: FavoritesCloudDataSource,
    private val toMemeEntityMapper: ToMemeEntityMapper,
    private val toMemeFirebaseMapper: ToMemeFirebaseMapper
) : FavoritesRepository.All {

    override suspend fun allMemes(): FavoritesResult {

        val cacheMemes = cacheDataSource.allMemes()

        return if (cacheMemes.isEmpty())
            FavoritesResult.Empty
        else
            FavoritesResult.Success(memes = cacheMemes.map {
                FavoriteMeme(
                    postLink = it.postLink,
                    subreddit = it.subreddit,
                    title = it.title,
                    url = it.url,
                    nsfw = it.nsfw,
                    author = it.author,
                    imageData = it.imageData
                )
            })
    }

    override suspend fun addMeme(userId: String, meme: FavoriteMeme) {
        cacheDataSource.add(meme.map(toMemeEntityMapper))

        cloudDataSource.add(
            userId = userId,
            favoriteMeme = meme.map(toMemeFirebaseMapper)
        )
    }


    override suspend fun removeMeme(postLink: String) = cacheDataSource.remove(postLink)
}