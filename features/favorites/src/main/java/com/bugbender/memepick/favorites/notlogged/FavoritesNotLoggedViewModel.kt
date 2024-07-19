package com.bugbender.memepick.favorites.notlogged

import androidx.lifecycle.ViewModel
import com.bugbender.memepick.presentation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesNotLoggedViewModel @Inject constructor(
    private val navigation: Navigation.Update
) : ViewModel() {

    fun goToAuthentication() {
        navigation.goToAuthenticationFromFavorites()
    }
}