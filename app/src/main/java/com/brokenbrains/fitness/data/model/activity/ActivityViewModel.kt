package com.brokenbrains.fitness.data.model.activity

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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActivityTodayState(
    val activityType: ActivityType,
    val value: String,
    val unit: String,
)

data class ActivityUiState(
    var allActivities: List<ActivityModel> = listOf(),
    var activityColumnarDataByType: Map<ActivityType, List<ColumnarData>> = mapOf(),
    var activityTodayByType: Map<ActivityType, ActivityTodayState> = mapOf(),
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
            repository.getAllActivities().flowOn(Dispatchers.IO).collect {
                _allActivities = it.toMutableList()
                val columnarDataList = getLast7DaysColumnarData()
                _uiState.value = ActivityUiState(
                    allActivities = _allActivities,
                    activityColumnarDataByType = columnarDataList,
                    activityTodayByType = _activityTodayByType,
                )
            }
        }
    }

    var _allActivities = mutableListOf<ActivityModel>()
    var _activityTodayByType = mutableMapOf<ActivityType, ActivityTodayState>()


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

            // find the max value
            val maxValue = columnarDataList.maxOf { it.value } // seconds
            // store in uiState if it's today
            if (dayOfWeeks.last().label === columnarDataList.last().label) { // cannot do this, we have same label in Tue and Thur
                _activityTodayByType.put(
                    activityType,
                    ActivityTodayState(
                        activityType = activityType,
                        value = CalendarUtils.getBiggestUnitStringOfTime(maxValue.toLong()).value,
                        unit = CalendarUtils.getBiggestUnitStringOfTime(maxValue.toLong()).unit
                    )
                )
            }
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


}

