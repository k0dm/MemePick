package com.bugbender.memepick.data.favorites.api

interface FavoriteContainsInCache{

    suspend fun contains(postLink: String): Boolean
}