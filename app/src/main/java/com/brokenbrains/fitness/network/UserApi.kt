package com.brokenbrains.fitness.network

import com.brokenbrains.fitness.network.request.RegisterRequest
import com.brokenbrains.fitness.network.response.UserResponse
import retrofit2.http.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserApi @Inject constructor(private val service: Service) {
    // note: BASE_URL is built in build.gradle and inject with DI

    suspend fun login(email: String, password: String): UserResponse {
        return service.login(email, password)
    }

    suspend fun register(request: RegisterRequest): UserResponse {
        return service.register(request)
    }


    interface Service {
        @FormUrlEncoded
        @GET("user/login")
        suspend fun login(
            @Query("email") email: String,
            @Query("password") password: String
        ): UserResponse

        @POST("user/register")
        suspend fun register(
            @Body RegisterRequest: RegisterRequest
        ): UserResponse
    }

}