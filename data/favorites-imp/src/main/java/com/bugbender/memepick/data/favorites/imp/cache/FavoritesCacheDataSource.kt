package com.bugbender.memepick.data.favorites.imp.cache

import com.bugbender.memepick.data.favorites.api.FavoriteContainsInCache
import javax.inject.Inject

interface FavoritesCacheDataSource : FavoriteContainsInCache {

    suspend fun add(memeEntity: MemeEntity)

    suspend fun allMemes(): List<MemeEntity>

    suspend fun remove(postLink: String)

    class Base @Inject constructor(private val dao: FavoritesDao) : FavoritesCacheDataSource {

        override suspend fun add(memeEntity: MemeEntity) = dao.add(memeEntity)

        override suspend fun allMemes(): List<MemeEntity> = dao.memes()

        override suspend fun remove(postLink: String) = dao.removeByPostLink(postLink = postLink)

        override suspend fun contains(postLink: String): Boolean =
            dao.findByPostLink(postLink = postLink) != null
    }
}