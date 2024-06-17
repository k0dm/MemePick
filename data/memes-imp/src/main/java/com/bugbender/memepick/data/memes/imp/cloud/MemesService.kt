package com.bugbender.memepick.data.memes.imp.cloud

import retrofit2.Call
import retrofit2.http.GET

interface MemesService {

    @GET("/gimme")
    fun randomMeme(): Call<MemeDto>
}