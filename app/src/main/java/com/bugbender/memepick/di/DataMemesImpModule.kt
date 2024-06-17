package com.bugbender.memepick.di

import androidx.lifecycle.ViewModel
import com.bugbender.memepick.data.memes.api.MemesRepository
import com.bugbender.memepick.data.memes.imp.BaseMemesRepository
import com.bugbender.memepick.data.memes.imp.cloud.MemesCloudDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataMemesImpModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMemesCloudDataSource(cloudDataSource: MemesCloudDataSource.Base): MemesCloudDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindMemesRepository(repository: BaseMemesRepository): MemesRepository
}