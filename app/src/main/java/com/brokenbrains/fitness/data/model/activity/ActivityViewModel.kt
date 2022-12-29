package com.brokenbrains.fitness.data.model.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.ActivityRepository
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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

    private val _uiState = MutableStateFlow<ActivityUiState>(ActivityUiState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default)  {
            refresh()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.getAllActivities().flowOn(Dispatchers.IO).collect {
                _allActivities = it.toMutableList()
                val columnarDataList = getLast7DaysColumnarData()
                _uiState.update {
                    it.copy(
                        allActivities = _allActivities,
                        activityColumnarDataByType = columnarDataList,
                        activityTodayByType = _activityTodayByType
                    )
                }
//                _uiState.value = ActivityUiState(
//                    allActivities = _allActivities,
//                    activityColumnarDataByType = columnarDataList,
//                    activityTodayByType = _activityTodayByType,
//                )
            }
        }
    }

    var _allActivities = mutableListOf<ActivityModel>()
    var _activityTodayByType = mutableMapOf<ActivityType, ActivityTodayState>()


    fun addNewActivity(activityModel: ActivityModel) {
        viewModelScope.launch(Dispatchers.Default) {
            if (activityModel.startAt!! < activityModel.endAt!!) {
                repository.addNewActivity(activityModel = activityModel)
            }
            updateData()
        }
    }

    fun updateData(){
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy())
        }
    }

    /*   fun calculateEnergyExpandLast7Days(): Int {
           var totalEnergyExpand = 0
           val scale = 10；
           val columnarDataList = mutableListOf<ColumnarData>()
           val dayOfWeeks = CalendarUtils.getLast7Days()
           if (_allActivities.isNotEmpty()) {
               for (dayOfWeek in dayOfWeeks) {
                   val activities = _allActivities.filter { it.startAt?.dayOfWeek == dayOfWeek }
                   var energyExpand = 0
                   for (activity in activities) {
                       energyExpand += activity.energyExpand
                   }
                   totalEnergyExpand += energyExpand
                   columnarDataList.add(ColumnarData(dayOfWeek, energyExpand / scale))
               }
           }
           return totalEnergyExpand
       }*/

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

            // store today's value
            columnarDataList.last().let {
                val formatted = CalendarUtils.getBiggestUnitStringOfTime(it.value.toLong())
                _activityTodayByType.put(
                    activityType,
                    ActivityTodayState(
                        activityType = activityType,
                        value = formatted.value,
                        unit = formatted.unit
                    )
                )
            }

            // find the max value in the list and normalize the values
            val maxValue = columnarDataList.maxOf { it.value } // seconds
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

