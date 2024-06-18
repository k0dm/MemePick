package com.bugbender.memepick.memes.presentation

import com.bugbender.memepick.data.memes.api.MemeResult
import javax.inject.Inject

class MemeResultMapper @Inject constructor(
    private val liveDataWrapper: MemesLiveDataWrapper
) : MemeResult.Mapper {

    override fun mapSuccess(
        postLink: String,
        subreddit: String,
        title: String,
        url: String,
        nsfw: Boolean,
        author: String
    ) {
        liveDataWrapper.updateUi(
            MemesUiState.Base(
                postLink = postLink,
                subreddit = subreddit,
                title = title,
                url = url,
                nsfw = nsfw,
                author = author
            )
        )
    }

    override fun mapError(message: String) {
        liveDataWrapper.updateUi(MemesUiState.Error(message = message))
    }
}