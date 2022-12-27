package com.brokenbrains.fitness.data.repository

import com.brokenbrains.fitness.data.dao.UserDao
import com.brokenbrains.fitness.network.UserApi
import com.brokenbrains.fitness.network.request.RegisterRequest
import com.brokenbrains.fitness.network.response.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
//    private val userDataStore: UserDataStore,
    private val userApi: UserApi
) {

    // connect to Room


    suspend fun logout() {
        // clear the data


    }

    suspend fun login(username: String, password: String): UserResponse {
        val res = userApi.login(username, password)
        return res
    }

    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): UserResponse {
//         save the data and register
        val res = userApi.register(
            RegisterRequest(
                firstName,
                lastName,
                email,
                password,
            )
        )
        return res

    }

}