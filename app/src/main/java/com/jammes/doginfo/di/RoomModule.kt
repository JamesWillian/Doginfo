package com.jammes.doginfo.di

import android.app.Application
import com.jammes.doginfo.core.database.AppDatabase
import com.jammes.doginfo.core.database.dao.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun providesDogDao(database: AppDatabase): DogDao {
        return database.dogDao()
    }
}