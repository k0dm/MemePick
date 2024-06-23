package com.bugbender.memepick.data.favorites.imp.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meme")
data class MemeEntity(
    @PrimaryKey
    @ColumnInfo(name = "post_link")
    val postLink: String,
    @ColumnInfo(name = "subreddit")
    val subreddit: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "nsfw")
    val nsfw: Boolean,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "image_data")
    val imageData: ByteArray
)