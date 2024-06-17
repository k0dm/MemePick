package com.bugbender.memepick.data.memes.imp.cloud

import javax.inject.Inject

interface MemesCloudDataSource {

    suspend fun randomMeme(): MemeDto

    class Base @Inject constructor(private val memesService: MemesService) : MemesCloudDataSource {

        override suspend fun randomMeme(): MemeDto {
            return memesService.randomMeme().execute().body()!!
        }
    }
}


