package com.brokenbrains.fitness.data.model.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.ActivityRepository
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActivityUiState(
    var allActivities: List<ActivityModel> = listOf(),
    var ColumnarDataByType: Map<ActivityType, List<ColumnarData>> = mapOf(),
)


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: ActivityRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivityUiState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            refresh()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            /*_allActivities = */repository.getAllActivities().flowOn(Dispatchers.IO).collect {
//                _uiState.value = _uiState.value.copy(allActivities = it)
            _allActivities = it.toMutableList()
            val columnarDataList = getLast7DaysColumnarData()
            _uiState.value = ActivityUiState(
                allActivities = _allActivities,
                ColumnarDataByType = columnarDataList
            )
        }
//            _uiState.value = _uiState.value.copy(allActivities = _allActivities)
//            _allActivities = newActivities.toMutableList()
//            uiState = uiState.copy(allActivities = newActivities)
//            uiState = uiState.copy(ColumnarDataByType = columnarDataList.toMap())
        }
    }

    var _allActivities = mutableListOf<ActivityModel>()


    fun addNewActivity(activityModel: ActivityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            if (activityModel.startAt!! < activityModel.endAt!!) {
                repository.addNewActivity(activityModel = activityModel)
            }
        }
    }

    fun getLast7DaysColumnarDataByType(activityType: ActivityType): List<ColumnarData> {
        val columnarDataList = mutableListOf<ColumnarData>()
        val dayOfWeeks = CalendarUtils.getLast7Days()
        if (_allActivities.isNotEmpty()) {
            val models =
                _allActivities.filter { it.activityType == activityType }.sortedBy { it.startAt }
            // sum all activities in a day
            for (day in dayOfWeeks) {
                var sum = 0f
                for (model in models) {
                    if (CalendarUtils.isSameDay(day, model.startAt)) {
                        sum += model.duration().toFloat()
                    }
                }
                columnarDataList.add(ColumnarData(label = day.label, value = sum))
            }

            /*    models
                    .takeLast(7) // TODO fake: takeLast(7) should then filter by dayOfWeek
                    .mapIndexed { index, it ->
                        columnarDataList.add(
                            ColumnarData(
                                value = it.duration().toFloat(),
                                label = dayOfWeeks[index].label
                            )
                        )
                    }
                    */
            // find the max value
            val maxValue = columnarDataList.maxOf { it.value }
            // normalize the values
            columnarDataList.map {
                it.value = it.value / maxValue
            }
        } else {
            dayOfWeeks.map {
                columnarDataList.add(
                    ColumnarData(
                        value = 0f,
                        label = it.label
                    )
                )
            }
        }
        return columnarDataList
    }


    private fun getLast7DaysColumnarData(): MutableMap<ActivityType, List<ColumnarData>> {
        val columnarDataList = mutableMapOf<ActivityType, List<ColumnarData>>()
        ActivityType.values().map {
            val columnarData = getLast7DaysColumnarDataByType(it)
            columnarDataList.put(it, columnarData)
        }
        return columnarDataList
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

