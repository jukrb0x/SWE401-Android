package com.brokenbrains.fitness.data.model.measurement

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.model.measurement.MeasurementModel
import com.brokenbrains.fitness.data.model.measurement.MeasurementType
import com.brokenbrains.fitness.data.repository.MeasurementRepository
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

data class MeasurementTodayState(
    val MeasurementType: MeasurementType,
    val value: String,
    val unit: String,
)

data class MeasurementUiState(
    var allActivities: List<MeasurementModel> = listOf(),
    var MeasurementColumnarDataByType: Map<MeasurementType, List<ColumnarData>> = mapOf(),
    var MeasurementTodayByType: Map<MeasurementType, MeasurementTodayState> = mapOf(),
)

@HiltViewModel
class MeasurementViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MeasurementRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MeasurementUiState())
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
                _uiState.value = MeasurementUiState(
                    allActivities = _allActivities,
                    MeasurementColumnarDataByType = columnarDataList,
                    MeasurementTodayByType = _MeasurementTodayByType,
                )
            }
        }
    }

    var _allActivities = mutableListOf<MeasurementModel>()
    var _MeasurementTodayByType = mutableMapOf<MeasurementType, MeasurementTodayState>()


    fun addNewMeasurement(MeasurementModel: MeasurementModel) {
        CoroutineScope(Dispatchers.IO).launch {
            if (MeasurementModel.startAt!! < MeasurementModel.endAt!!) {
                repository.addNewMeasurement(MeasurementModel = MeasurementModel)
            }
        }
    }

    fun getLast7DaysColumnarDataByType(MeasurementType: MeasurementType): List<ColumnarData> {
        val columnarDataList = mutableListOf<ColumnarData>()
        val dayOfWeeks = CalendarUtils.getLast7Days()
        if (_allActivities.isNotEmpty()) {
            val models =
                _allActivities.filter { it.MeasurementType == MeasurementType }.sortedBy { it.startAt }
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
                _MeasurementTodayByType.put(
                    MeasurementType,
                    MeasurementTodayState(
                        MeasurementType = MeasurementType,
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


    private fun getLast7DaysColumnarData(): MutableMap<MeasurementType, List<ColumnarData>> {
        val columnarDataList = mutableMapOf<MeasurementType, List<ColumnarData>>()
        MeasurementType.values().map {
            val columnarData = getLast7DaysColumnarDataByType(it)
            columnarDataList.put(it, columnarData)
        }
        return columnarDataList
    }


}

