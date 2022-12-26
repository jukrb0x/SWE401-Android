package com.brokenbrains.fitness.data.repository

import com.brokenbrains.fitness.data.AppDatabase
import com.brokenbrains.fitness.network.TestApi
import com.brokenbrains.fitness.network.TestResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val appDatabase: AppDatabase,
//    private val userDataStore: UserDataStore,
    private val testApi: TestApi
) {

    // connect to Room

    suspend fun login(): TestResponse {
        val res = testApi.getTestMessage()
        return res
    }


    suspend fun logout() {

    }


}