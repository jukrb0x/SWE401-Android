package com.brokenbrains.fitness.network

import com.brokenbrains.fitness.network.response.TestResponse
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestApi @Inject constructor(private val service: Service) {
    // note: BASE_URL is built in build.gradle and inject with DI

    suspend fun getTestMessage() = service.getTestMessage()


    interface Service {
        @GET("test")
        suspend fun getTestMessage(): TestResponse
    }

}