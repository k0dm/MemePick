package com.bugbender.memepick.data.memes.imp.cloud

import com.google.gson.annotations.SerializedName

data class MemeDto(
    @SerializedName("postLink")
    val postLink: String,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("nsfw")
    val nsfw: Boolean,
    @SerializedName("author")
    val author: String,
    @SerializedName("preview")
    val previews: List<String>
)