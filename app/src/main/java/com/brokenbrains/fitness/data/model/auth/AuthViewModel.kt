package com.brokenbrains.fitness.data.model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.AuthRepository
import com.brokenbrains.fitness.network.ResultData
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Firebase Auth ViewModel
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
)
:ViewModel() {

    private val _loginFlow = MutableStateFlow<ResultData<FirebaseUser>?>(null)
    val loginFlow: StateFlow<ResultData<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<ResultData<FirebaseUser>?>(null)
    val signupFlow: StateFlow<ResultData<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if(repository.currentUser != null){
            _loginFlow.value = ResultData.Success(repository.currentUser!!)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = ResultData.Loading()
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = ResultData.Loading()
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout() {
        _signupFlow.value = null
        _loginFlow.value = null
        repository.logout()
    }

    // clean up flows after consuming them in some use case
    // workaround for re-compose
    fun cleanUp(){
        _signupFlow.value = null
        _loginFlow.value = null
    }
}