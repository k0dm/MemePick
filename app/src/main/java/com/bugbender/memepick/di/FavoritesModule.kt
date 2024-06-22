package com.bugbender.memepick.di

import com.bugbender.memepick.favorites.FavoritesLiveDataWrapper
import com.bugbender.memepick.favorites.FavoritesResultMapper
import com.bugbender.memepick.favorites.ToFavoriteUiMemeMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoritesModule {

    @Binds
    @ViewModelScoped
    abstract fun bindFavoritesLiveDataWrapper(liveDataWrapper: FavoritesLiveDataWrapper.Base): FavoritesLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindToFavoriteUiMapper(mapper: ToFavoriteUiMemeMapper.Base): ToFavoriteUiMemeMapper

    @Binds
    @ViewModelScoped
    abstract fun bindFavoritesResultMapper(mapper: FavoritesResultMapper.Base): FavoritesResultMapper
}