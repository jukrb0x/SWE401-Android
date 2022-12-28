package com.brokenbrains.fitness.di

import com.brokenbrains.fitness.AppState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
//    @Provides
//    @Singleton
//    fun providesAppstate = AppState(Vkk )
}