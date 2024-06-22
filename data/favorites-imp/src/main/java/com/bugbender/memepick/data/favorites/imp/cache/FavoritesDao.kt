package com.bugbender.memepick.data.favorites.imp.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(memeEntity: MemeEntity)

    @Query("SELECT * FROM favorite_meme")
    suspend fun memes(): List<MemeEntity>

    @Query("DELETE FROM favorite_meme WHERE id = :id")
    suspend fun remove(id: Long)
}