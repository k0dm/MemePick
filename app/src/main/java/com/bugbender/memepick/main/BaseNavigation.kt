package com.bugbender.memepick.main

import com.bugbender.memepick.authentication.AuthenticationScreen
import com.bugbender.memepick.presentation.LiveDataWrapper
import com.bugbender.memepick.presentation.Navigation.Mutable
import com.bugbender.memepick.presentation.Screen
import com.bugbender.profile.ProfileScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseNavigation @Inject constructor() : Mutable, LiveDataWrapper.Single<Screen>() {

    override fun goToAuthentication() = updateUi(AuthenticationScreen)

    override fun goToProfile() = updateUi(ProfileScreen)
}