package com.bugbender.memepick.main

import com.bugbender.memepick.authentication.AuthenticationScreen
import com.bugbender.memepick.authentication.SignInAuthResultMapper
import com.bugbender.memepick.favorites.FavoritesScreen
import com.bugbender.memepick.favorites.notlogged.FavoritesNotLoggedScreen
import com.bugbender.memepick.presentation.LiveDataWrapper
import com.bugbender.memepick.presentation.Navigation.Mutable
import com.bugbender.memepick.presentation.Screen
import com.bugbender.profile.ProfileScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseNavigation @Inject constructor() : Mutable, LiveDataWrapper.Single<Screen>() {

    override fun goToAuthenticationFromProfile() =
        updateUi(AuthenticationScreen(SignInAuthResultMapper.ToProfile::class.simpleName!!))

    override fun goToProfile() = updateUi(ProfileScreen)

    override fun goToFavoritesNotLogged() = updateUi(FavoritesNotLoggedScreen)

    override fun goToFavorites() = updateUi(FavoritesScreen)

    override fun goToAuthenticationFromFavorites() =
        updateUi(AuthenticationScreen(SignInAuthResultMapper.ToFavorites::class.simpleName!!))
}