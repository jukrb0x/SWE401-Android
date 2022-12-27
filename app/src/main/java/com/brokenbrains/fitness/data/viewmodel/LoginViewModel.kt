package com.brokenbrains.fitness.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class LoginUiState(
    val email: String = "", val password: String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: UserRepository
) : ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set


}