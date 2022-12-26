package com.brokenbrains.fitness.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.repository.UserRepository
import com.brokenbrains.fitness.network.TestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: UserRepository
) :ViewModel() {

    // livedata / state
    suspend fun login(username: String, password: String): TestResponse {
        val res = repository.login()
        return res

    }
//        repository.login()


    // method to get user data, login, logout...


    // init {} get  the init data

}