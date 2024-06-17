package com.bugbender.memepick.data.memes.imp

import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.data.memes.api.MemesRepository
import com.bugbender.memepick.data.memes.imp.cloud.MemesCloudDataSource
import com.bugbender.memepick.core.data.HandleError
import javax.inject.Inject

class BaseMemesRepository @Inject constructor(
    private val cloudDataSource: MemesCloudDataSource,
    private val handleError: HandleError
) : MemesRepository {

    override suspend fun randomMeme(): MemeResult {
        return try {
            val memeDto = cloudDataSource.randomMeme()
            MemeResult.Success(
                postLink = memeDto.postLink,
                subreddit = memeDto.subreddit,
                title = memeDto.title,
                url = memeDto.url,
                nsfw = memeDto.nsfw,
                author = memeDto.author
            )
        } catch (e: Exception) {
            MemeResult.Error(handleError.handle(e))
        }
    }
}