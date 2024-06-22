package com.bugbender.memepick.data.favorites.imp.cache

import javax.inject.Inject

interface FavoriteCacheDataSource {

    suspend fun add(memeEntity: MemeEntity)

    suspend fun allMemes(): List<MemeEntity>

    suspend fun remove(id: Long)

    class Base @Inject constructor(private val dao: FavoritesDao) : FavoriteCacheDataSource {

        override suspend fun add(memeEntity: MemeEntity) = dao.add(memeEntity)

        override suspend fun allMemes(): List<MemeEntity> = dao.memes()

        override suspend fun remove(id: Long) = dao.remove(id = id)
    }
}