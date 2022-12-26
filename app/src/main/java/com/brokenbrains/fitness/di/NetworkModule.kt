package com.brokenbrains.fitness.di

import com.brokenbrains.fitness.BuildConfig
import com.brokenbrains.fitness.network.ServiceBuilder
import com.brokenbrains.fitness.network.TestApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesAuthInterceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()


    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesTestApi(
        serviceBuilder: ServiceBuilder
    ): TestApi.Service {
        return serviceBuilder.buildService(TestApi.Service::class.java)
    }

}