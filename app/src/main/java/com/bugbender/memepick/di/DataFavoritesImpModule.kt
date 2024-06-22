package com.bugbender.memepick.di

import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.data.favorites.imp.BaseFavoriteRepository
import com.bugbender.memepick.data.favorites.imp.cache.FavoriteCacheDataSource
import com.bugbender.memepick.data.favorites.imp.cache.ToMemeEntityMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataFavoritesImpModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsFavoriteCacheDataSource(datasource: FavoriteCacheDataSource.Base): FavoriteCacheDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindToMemeEntityMapper(mapper: ToMemeEntityMapper.Base): ToMemeEntityMapper

    @Binds
    @ViewModelScoped
    abstract fun bindsGetAndRemoveRepository(repository: BaseFavoriteRepository): FavoritesRepository.GetAndRemove

    @Binds
    @ViewModelScoped
    abstract fun bindsAddAndRemoveRepository(repository: BaseFavoriteRepository): FavoritesRepository.AddAndRemove
}