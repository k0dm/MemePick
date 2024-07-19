package com.bugbender.memepick.data.favorites.imp.cloud

import com.bugbender.mempick.core.firebase.FireStore
import javax.inject.Inject

interface FavoritesCloudDataSource {

    suspend fun add(userId: String, favoriteMeme: HashMap<String, Any>): String

    class Base @Inject constructor(private val fireStore: FireStore) : FavoritesCloudDataSource {

        private val topLevelCollector = "users"
        private val userFavoritesCollection = "favorites"

        override suspend fun add(userId: String, favoriteMeme: HashMap<String, Any>): String {
            return fireStore.addToSub(
                collection = topLevelCollector,
                document = userId,
                subCollection = userFavoritesCollection,
                data = favoriteMeme
            )
        }
    }
}