package com.bugbender.memepick

import androidx.lifecycle.LiveData
import com.bugbender.memepick.main.MainViewModel
import com.bugbender.memepick.memes.presentation.MemesScreen
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.memepick.presentation.Screen
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var navigation: FakeNavigation

    @Before
    fun setUp() {
        navigation = FakeNavigation()
        viewModel = MainViewModel(navigation = navigation)
    }

    @Test
    fun testFirstRun() {
        viewModel.init(isFirstRun = true)
        navigation.checkScreens(MemesScreen)
    }

    @Test
    fun testNotFirstRun() {
        viewModel.init(isFirstRun = false)
        navigation.checkScreens()
    }
}

private class FakeNavigation : Navigation.Mutable {

    private val actualScreens: MutableList<Screen> = mutableListOf()
    override fun goToAuthentication() {
        TODO("Not yet implemented")
    }

    override fun goToProfile() {
        TODO("Not yet implemented")
    }

    override fun updateUi(value: Screen) {
        actualScreens.add(value)
    }

    fun checkScreens(vararg expectedScreens: Screen) {
        assertEquals(expectedScreens.toList(), actualScreens)
    }

    override fun liveData(): LiveData<Screen> =
        throw IllegalStateException("Don't use in unit tests")
}