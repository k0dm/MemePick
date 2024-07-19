package com.bugbender.memepick.di

import com.bugbender.memepick.authentication.SignInAuthResultMapper
import com.bugbender.memepick.authentication.ToFavoritesMapper
import com.bugbender.memepick.authentication.ToProfileMapper
import com.bugbender.memepick.presentation.MessageLiveDataWrapper
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.mempick.core.firebase.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AuthenticationModule {

    @Binds
    abstract fun bindMessageLiveDataWrapper(liveDataWrapper: MessageLiveDataWrapper.Base): MessageLiveDataWrapper

    @Binds
    abstract fun bindAuthRepositoryLoginWithGoogle(repository: AuthRepository.Base): AuthRepository.LoginWithGoogle

    companion object {
        @Provides
        @ToProfileMapper
        fun provideToProfileSignInAuthResultMapper(
            navigation: Navigation.Update,
            messageLiveDataWrapper: MessageLiveDataWrapper
        ): SignInAuthResultMapper = SignInAuthResultMapper.ToProfile(
            navigation = navigation,
            messageLiveDataWrapper = messageLiveDataWrapper
        )

        @Provides
        @ToFavoritesMapper
        fun provideToFavoritesSignInAuthResultMapper(
            navigation: Navigation.Update,
            messageLiveDataWrapper: MessageLiveDataWrapper
        ): SignInAuthResultMapper = SignInAuthResultMapper.ToFavorites(
            navigation = navigation,
            messageLiveDataWrapper = messageLiveDataWrapper
        )
    }
}