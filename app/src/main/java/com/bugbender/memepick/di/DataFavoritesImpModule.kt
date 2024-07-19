package com.bugbender.memepick.di

import com.bugbender.memepick.data.favorites.api.FavoriteContainsInCache
import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.data.favorites.imp.BaseFavoriteRepository
import com.bugbender.memepick.data.favorites.imp.cache.FavoritesCacheDataSource
import com.bugbender.memepick.data.favorites.imp.cache.ToMemeEntityMapper
import com.bugbender.memepick.data.favorites.imp.cache.ToMemeFirebaseMapper
import com.bugbender.memepick.data.favorites.imp.cloud.FavoritesCloudDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataFavoritesImpModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsFavoriteCacheDataSource(datasource: FavoritesCacheDataSource.Base): FavoritesCacheDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindFavoritesCacheDataSourceContains(datasource: FavoritesCacheDataSource.Base): FavoriteContainsInCache

    @Binds
    @ViewModelScoped
    abstract fun bindFavoritesCloudDataSource(datasource: FavoritesCloudDataSource.Base): FavoritesCloudDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindToMemeEntityMapper(mapper: ToMemeEntityMapper.Base): ToMemeEntityMapper

    @Binds
    @ViewModelScoped
    abstract fun bindToMemeFirebaseMapper(mapper: ToMemeFirebaseMapper.Base): ToMemeFirebaseMapper

    @Binds
    @ViewModelScoped
    abstract fun bindsGetAndRemoveRepository(repository: BaseFavoriteRepository): FavoritesRepository.GetAndRemove

    @Binds
    @ViewModelScoped
    abstract fun bindsAddAndRemoveRepository(repository: BaseFavoriteRepository): FavoritesRepository.AddAndRemove
}