package com.bugbender.memepick.data.favorites.imp.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [MemeEntity::class])
abstract class MemesDatabase: RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}