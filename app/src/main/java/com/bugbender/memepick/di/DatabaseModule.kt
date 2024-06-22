package com.bugbender.memepick.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bugbender.memepick.data.favorites.imp.cache.FavoritesDao
import com.bugbender.memepick.data.favorites.imp.cache.MemesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MemesDatabase = Room.databaseBuilder(
        context = context,
        klass = MemesDatabase::class.java,
        "memes_db"
    ).build()

    @Provides
    fun provideFavoritesDao(database: MemesDatabase): FavoritesDao = database.favoritesDao()
}