package com.bugbender.memepick.di

import androidx.lifecycle.ViewModel
import com.bugbender.memepick.data.memes.api.MemeResult
import com.bugbender.memepick.memes.presentation.MemeResultMapper
import com.bugbender.memepick.memes.presentation.MemesLiveDataWrapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class FeatureMemesModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLiveDataWrapper(liveDataWrapper: MemesLiveDataWrapper.Base): MemesLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindMapper(mapper: MemeResultMapper): MemeResult.Mapper
}