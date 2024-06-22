package com.bugbender.memepick.memes

import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.data.memes.api.MemesRepository
import com.bugbender.memepick.memes.presentation.MemesLiveDataWrapper
import com.bugbender.memepick.memes.presentation.MemesUiState
import com.bugbender.memepick.memes.presentation.MemesViewModel
import com.bugbender.memepick.presentation.RunAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MemesViewModelTest {

    private lateinit var viewModel: MemesViewModel
    private lateinit var repository: FakeMemesRepository
    private lateinit var liveDataWrapper: FakeMemesLiveDataWrapper
    private lateinit var mapper: FakeMemeResultMapper
    private lateinit var runAsync: FakeRunAsync

    @Before
    fun setUp() {
        repository = FakeMemesRepository()
        liveDataWrapper = FakeMemesLiveDataWrapper()
        mapper = FakeMemeResultMapper()
        runAsync = FakeRunAsync()
        viewModel = MemesViewModel(
            memesRepository = repository,
            liveDataWrapper = liveDataWrapper,
            mapper = mapper,
            runAsync = runAsync
        )
    }

    @Test
    fun testMainScenario() {
        viewModel.init(isFirstRun = true)
        liveDataWrapper.checkUiState(MemesUiState.Progress)
        runAsync.pingUiBlock()
        mapper.checkMapSuccessCalled(1)
        mapper.checkMapErrorCalled(0)

        repository.returnError()

        viewModel.getMeme()
        liveDataWrapper.checkUiState(MemesUiState.Progress)
        runAsync.pingUiBlock()
        mapper.checkMapSuccessCalled(1)
        mapper.checkMapErrorCalled(1)

        repository.returnSuccess()

        viewModel.getMeme()
        liveDataWrapper.checkUiState(MemesUiState.Progress)
        runAsync.pingUiBlock()
        mapper.checkMapSuccessCalled(2)
        mapper.checkMapErrorCalled(1)
    }
}

private class FakeMemesRepository : MemesRepository {

    private var returnSuccess = true

    override suspend fun randomMeme(): MemeResult {
        return if (returnSuccess) {
            MemeResult.Success(
                postLink = "postLink1",
                subreddit = "subreddit1",
                title = "title1",
                url = "url1",
                nsfw = false,
                author = "author1"
            )
        } else {
            MemeResult.Error(message = "No internet connection")
        }
    }

    fun returnError() {
        returnSuccess = false
    }

    fun returnSuccess() {
        returnSuccess = true
    }
}

internal class FakeMemesLiveDataWrapper () : MemesLiveDataWrapper {

    private var actualUiState: MemesUiState = MemesUiState.Empty

    override fun updateUi(value: MemesUiState) {
        actualUiState = value
    }

    fun checkUiState(expectedUiState: MemesUiState) {
        assertEquals(expectedUiState, actualUiState)
    }
}


private class FakeMemeResultMapper : MemeResult.Mapper {

    private var actualMapSuccessCalled = 0

    override fun mapSuccess(
        postLink: String,
        subreddit: String,
        title: String,
        url: String,
        nsfw: Boolean,
        author: String
    ) {
        actualMapSuccessCalled++
    }

    fun checkMapSuccessCalled(expectedTimes: Int) {
        assertEquals(expectedTimes, actualMapSuccessCalled)
    }

    private var actualMapErrorCalled = 0

    override fun mapError(message: String) {
        actualMapErrorCalled++
    }

    fun checkMapErrorCalled(expectedTimes: Int) {
        assertEquals(expectedTimes, actualMapErrorCalled)
    }
}

private class FakeRunAsync : RunAsync {

    private var backgroundResult: Any = Any()
    private var cachedUiBlock: (Any) -> Unit = {}

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> start(
        coroutineScope: CoroutineScope,
        backgroundBlock: suspend () -> T,
        uiBlock: (T) -> Unit
    ) = runBlocking {
        backgroundResult = backgroundBlock.invoke()
        cachedUiBlock = uiBlock as (Any) -> Unit
    }

    fun pingUiBlock() {
        cachedUiBlock.invoke(backgroundResult)
    }
}