package com.brokenbrains.fitness.data.model.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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

    var _allActivities = MutableStateFlow(listOf<ActivityModel>())
    val allActivities: StateFlow<List<ActivityModel>>
        get() = _allActivities

    init{
        getAllActivities()
    }
    fun getAllActivities() {
        viewModelScope.launch {
            repository.getAllActivities().catch { e ->
                e.printStackTrace()
            }.collect {
                _allActivities.value = it
            }
        }
    }


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
//    fun getLast7DaysOfActivityByTypes(types: List<ActivityType>) = repository.getLast7DaysOfActivityByTypes(types)

    // init {} get  the init data

}