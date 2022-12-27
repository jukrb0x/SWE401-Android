package com.brokenbrains.fitness.di

import android.app.Application
import androidx.room.Room
import com.brokenbrains.fitness.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "mariofit.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    @Singleton
    fun provideActivityDao(appDatabase: AppDatabase) = appDatabase.activityDao()

//    @Provides
//    @Singleton
//    fun providesUserDataStore(application: Application) : DataStore<UserDataStore> {
//        return UserDataStore.create(application)
//    }

}