package com.brokenbrains.fitness.data.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: ActivityRepository

) : ViewModel() {

//    val uiState

    val allActivities = repository.getAllActivities()

    fun addNewActivity(activityModel: ActivityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNewActivity(activityModel = activityModel)
        }
    }


    // init {} get  the init data

}