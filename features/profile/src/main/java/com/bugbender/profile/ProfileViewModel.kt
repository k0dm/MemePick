package com.bugbender.profile

import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.memepick.presentation.RunAsync
import com.bugbender.mempick.core.firebase.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigation: Navigation.Update,
    private val authRepository: AuthRepository.All,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun checkIsUserLoggedIn() {
        if (!authRepository.isUserLogged()) {
            navigation.goToAuthenticationFromProfile()
        }
    }

    fun userEmail() = authRepository.userEmail()

    fun logOut() {
        authRepository.signOut()
        navigation.goToAuthenticationFromProfile()
    }
}