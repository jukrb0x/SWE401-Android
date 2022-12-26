package com.brokenbrains.fitness.data.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.repository.UserRepository
import com.brokenbrains.fitness.network.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: UserRepository
) : ViewModel() {

    // livedata / state
    suspend fun login(username: String, password: String): UserResponse {
        val res = repository.login(username, password)
        return res
    }

    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): UserResponse {
        val res = repository.register(
            firstName,
            lastName,
            email,
            password,
        )
        return res
    }

    suspend fun logout() {
        repository.logout()
    }

    fun signup(email: String, password: String, firstName: String, lastName: String) {

    }


    // method to get user data, login, logout...


    // init {} get  the init data

}