package com.bugbender.memepick.data.favorites.imp

import com.bugbender.memepick.data.favorites.api.FavoriteMeme
import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.data.favorites.api.FavoritesResult
import com.bugbender.memepick.data.favorites.imp.cache.FavoriteCacheDataSource
import com.bugbender.memepick.data.favorites.imp.cache.ToMemeEntityMapper
import javax.inject.Inject

class BaseFavoriteRepository @Inject constructor(
    private val cacheDataSource: FavoriteCacheDataSource,
    private val toMemeEntityMapper: ToMemeEntityMapper,
) : FavoritesRepository.All {

    override suspend fun allMemes(): FavoritesResult {

        val cacheMemes = cacheDataSource.allMemes()

        return if (cacheMemes.isEmpty())
            FavoritesResult.Empty
        else
            FavoritesResult.Success(memes = cacheMemes.map {
                FavoriteMeme(
                    id = it.id,
                    postLink = it.postLink,
                    subreddit = it.subreddit,
                    title = it.title,
                    url = it.url,
                    nsfw = it.nsfw,
                    author = it.author
                )
            })
    }

    override suspend fun addMeme(meme: FavoriteMeme) =
        cacheDataSource.add(meme.map(toMemeEntityMapper))

    override suspend fun removeMeme(id: Long) = cacheDataSource.remove(id)
}