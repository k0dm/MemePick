package com.bugbender.memepick.di

import com.bugbender.memepick.core.data.HandleError
import com.bugbender.memepick.core.data.ProvideResources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDataModule {

    @Binds
    abstract fun bindProvideResources(provideResources: ProvideResources.Base): ProvideResources

    @Binds
    abstract fun bindHandleError(handleError: HandleError.Base): HandleError
}