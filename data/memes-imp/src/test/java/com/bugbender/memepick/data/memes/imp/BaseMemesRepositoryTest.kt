package com.bugbender.memepick.data.memes.imp

import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.data.memes.imp.cloud.MemeDto
import com.bugbender.memepick.data.memes.imp.cloud.MemesCloudDataSource
import com.bugbender.memepick.core.data.HandleError
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

//class BaseMemesRepositoryTest {
//
//    private lateinit var repository: BaseMemesRepository
//    private lateinit var cloudDataSource: FakeMemesCloudDataSource
//    private lateinit var handleError: FakeHandleError
//
//    @Before
//    fun setUp() {
//        cloudDataSource = FakeMemesCloudDataSource()
//        handleError = FakeHandleError()
//        repository = BaseMemesRepository(
//            cloudDataSource = cloudDataSource,
//            handleError = handleError
//        )
//    }
//
//    @Test
//    fun testSuccess() = runBlocking {
//        val actualResult = repository.randomMeme()
//        assertEquals(
//            MemeResult.Success(
//                postLink = "https://redd.it/1dh2ull",
//                subreddit = "dankmemes",
//                title = "Already hyped for the Silent Hill 2 remake",
//                url = "https://i.redd.it/k87ed5706w6d1.png",
//                nsfw = false,
//                author = "utolso_villamos",
//            ), actualResult
//        )
//    }
//
//    @Test
//    fun testError() = runBlocking {
//        cloudDataSource.returnError()
//
//        val actualResult = repository.randomMeme()
//        assertEquals(MemeResult.Error(message = UnknownHostException::class.java.simpleName), actualResult)
//    }
//}
//
//private class FakeMemesCloudDataSource : MemesCloudDataSource {
//
//    private var returnSuccess = true
//
//    override suspend fun randomMeme(): MemeDto = if (returnSuccess) {
//        MemeDto(
//            postLink = "https://redd.it/1dh2ull",
//            subreddit = "dankmemes",
//            title = "Already hyped for the Silent Hill 2 remake",
//            url = "https://i.redd.it/k87ed5706w6d1.png",
//            nsfw = false,
//            author = "utolso_villamos",
//        )
//    } else {
//        throw UnknownHostException()
//    }
//
//    fun returnError() {
//        returnSuccess = false
//    }
//}
//
//private class FakeHandleError : HandleError {
//
//    override fun handle(e: Exception): String = e.javaClass.simpleName
//}