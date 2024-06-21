package com.bugbender.memepick.di

import com.bugbender.memepick.authentication.SignInAuthResultMapper
import com.bugbender.memepick.presentation.MessageLiveDataWrapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthenticationModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMessageLiveDataWrapper(liveDataWrapper: MessageLiveDataWrapper.Base): MessageLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindSignInAuthResultMapper(mapper: SignInAuthResultMapper.Base): SignInAuthResultMapper
}