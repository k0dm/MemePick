package com.bugbender.memepick.di

import com.bugbender.memepick.presentation.Navigation
import com.bugbender.memepick.presentation.RunAsync
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindsNavigationMutable(navigation: Navigation.Base): Navigation.Mutable

    @Binds
    abstract fun bindRunAsync(runAsync: RunAsync.Base): RunAsync
}