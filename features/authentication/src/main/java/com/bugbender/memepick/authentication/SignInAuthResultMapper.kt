package com.bugbender.memepick.authentication

import com.bugbender.memepick.presentation.MessageLiveDataWrapper
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.mempick.core.firebase.AuthResult

interface SignInAuthResultMapper : AuthResult.Mapper {

    abstract class Base(
        private val messageLiveDataWrapper: MessageLiveDataWrapper
    ) : SignInAuthResultMapper {

        override fun mapError(message: String) {
            messageLiveDataWrapper.updateUi(value = message)
        }
    }

    @ToProfileMapper
    class ToProfile(
        private val navigation: Navigation.Update,
        messageLiveDataWrapper: MessageLiveDataWrapper
    ) : Base(messageLiveDataWrapper) {

        override fun mapSuccess() {
            navigation.goToProfile()
        }
    }

    @ToFavoritesMapper
    class ToFavorites(
        private val navigation: Navigation.Update,
        messageLiveDataWrapper: MessageLiveDataWrapper
    ) : Base(messageLiveDataWrapper) {

        override fun mapSuccess() {
            navigation.goToFavorites()
        }
    }
}