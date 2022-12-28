package com.brokenbrains.fitness.data.model.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.repository.ActivityRepository
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: ActivityRepository

) : ViewModel() {

//    val uiState = repository.uiState

//    init {
//
//    }
//
//    private fun setupObservers() {
//        val move = listOf(
//            ActivityType.WALKING,
//            ActivityType.RUNNING
//        )
//    }

    val allActivities = repository.getAllActivities()

    fun addNewActivity(activityModel: ActivityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNewActivity(activityModel = activityModel)
        }
    }
/*

    fun getLast7DaysOfActivityByTypes(types: List<ActivityType>): Flow<MutableList<ColumnarData>> {
//        val last7DaysAsColumnarData: MutableLiveData<MutableList<ColumnarData>> = MutableLiveData();
        var last7DaysAsColumnarData: Flow<MutableList<ColumnarData>> = emptyFlow()
            */
/*: MutableLiveData<MutableList<ColumnarData>> = MutableLiveData();*//*

        CoroutineScope(Dispatchers.IO).launch {
//            last7DaysAsColumnarData.value = repository.getLast7DaysOfActivityByTypes(types).value
            last7DaysAsColumnarData = repository.getLast7DaysOfActivityByTypes(types)
        }
        return last7DaysAsColumnarData;
//        return repository.getLast7DaysOfActivityByTypes(types)
    }

*/
    fun getLast7DaysOfActivityByTypes(types: List<ActivityType>) = repository.getLast7DaysOfActivityByTypes(types)

    // init {} get  the init data

}