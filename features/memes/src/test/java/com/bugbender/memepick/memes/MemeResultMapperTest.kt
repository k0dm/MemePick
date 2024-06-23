package com.bugbender.memepick.memes

import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.memes.presentation.MemeResultMapper
import com.bugbender.memepick.memes.presentation.MemesUiState
import org.junit.Before
import org.junit.Test

class MemeResultMapperTest {

//    private lateinit var mapper: MemeResult.Mapper
//    private lateinit var liveDataWrapper: FakeMemesLiveDataWrapper
//
//    @Before
//    fun setUp() {
//        liveDataWrapper = FakeMemesLiveDataWrapper()
//        mapper = MemeResultMapper(liveDataWrapper = liveDataWrapper)
//    }
//
//    @Test
//    fun test() {
//        liveDataWrapper.checkUiState(MemesUiState.Empty)
//
//        mapper.mapSuccess(
//            postLink = "postLink1",
//            subreddit = "subreddit1",
//            title = "title1",
//            url = "url1",
//            nsfw = false,
//            author = "author1"
//        )
//        liveDataWrapper.checkUiState(
//            MemesUiState.Base(
//                postLink = "postLink1",
//                subreddit = "subreddit1",
//                title = "title1",
//                url = "url1",
//                nsfw = false,
//                author = "author1"
//            )
//        )
//
//        mapper.mapError(message = "test-error")
//        liveDataWrapper.checkUiState(MemesUiState.Error(message = "test-error"))
//    }
}