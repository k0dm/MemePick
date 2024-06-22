package com.bugbender.memepick.data.favorites.api

interface FavoritesResult {

    interface Mapper {

        fun mapSuccess(memes: List<FavoriteMeme>)

        fun mapEmpty()
    }

    fun map(mapper: Mapper)

    data class Success(private val memes: List<FavoriteMeme>) : FavoritesResult {

        override fun map(mapper: Mapper) = mapper.mapSuccess(memes)
    }

    object Empty: FavoritesResult {

        override fun map(mapper: Mapper)  = mapper.mapEmpty()
    }
}