package com.bugbender.memepick.authentication

import com.bugbender.memepick.presentation.MessageLiveDataWrapper
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.mempick.core.firebase.AuthResult
import javax.inject.Inject

interface SignInAuthResultMapper : AuthResult.Mapper {

    class Base @Inject constructor(
        private val navigation: Navigation.Update,
        private val messageLiveDataWrapper: MessageLiveDataWrapper
    ) : SignInAuthResultMapper {

        override fun mapSuccess() {
            navigation.goToProfile()
        }

        override fun mapError(message: String) {
            messageLiveDataWrapper.updateUi(value = message)
        }
    }
}