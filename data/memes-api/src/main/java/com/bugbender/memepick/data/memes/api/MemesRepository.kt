package com.bugbender.memepick.data.memes.api

interface MemesRepository {

    suspend fun randomMeme(): MemeResult
}