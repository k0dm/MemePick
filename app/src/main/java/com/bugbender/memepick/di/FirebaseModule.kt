package com.bugbender.memepick.di

import com.bugbender.mempick.core.firebase.AuthRepository
import com.bugbender.mempick.core.firebase.FireStore
import dagger.Binds
import dagger.Module
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
    abstract fun bindAuthRepositoryUserIdAndCheck(repository: AuthRepository.Base): AuthRepository.UserIdAndCheck

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepositoryAll(repository: AuthRepository.Base): AuthRepository.All

    @Binds
    @ViewModelScoped
    abstract fun bindFireStore(fireStore: FireStore.Base): FireStore

//    companion object {
//
//        @Provides
//        fun provideAuth(): FirebaseAuth = Firebase.auth
//    }

}