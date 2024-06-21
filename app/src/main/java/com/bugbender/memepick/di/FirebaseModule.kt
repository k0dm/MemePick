package com.bugbender.memepick.di

import com.bugbender.mempick.core.firebase.AuthRepository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class FirebaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepositoryLoginWithGoogle(repository: AuthRepository.Base): AuthRepository.LoginWithGoogle

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(repository: AuthRepository.Base): AuthRepository

//    companion object {
//
//        @Provides
//        fun provideAuth(): FirebaseAuth = Firebase.auth
//    }

}