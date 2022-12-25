package com.brokenbrains.fitness.network

import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestApi @Inject constructor(private val service: Service){

    interface Service{
        @GET("/test")
        suspend fun getTestMessage()
    }


}