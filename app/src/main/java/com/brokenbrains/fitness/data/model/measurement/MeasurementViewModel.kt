package com.brokenbrains.fitness.data.model.measurement

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.MeasurementRepository
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MeasurementTodayState(
    val measurementType: MeasurementType,
    val value: String,
    val unit: String,
)

data class MeasurementUiState(
    var allMeasurements: List<MeasurementModel> = listOf(),
    var measurementColumnarDataByType: Map<MeasurementType, List<ColumnarData>> = mapOf(),
    var measurementTodayByType: Map<MeasurementType, MeasurementTodayState> = mapOf(),
)

@HiltViewModel
class MeasurementViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MeasurementRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MeasurementUiState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            refresh()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.getAllMeasurements().flowOn(Dispatchers.IO).collect {
                _allMeasurements = it.toMutableList()
                val columnarDataList = getLast7ColumnarData()
                _uiState.update {
                    it.copy(
                        allMeasurements = _allMeasurements,
                        measurementColumnarDataByType = columnarDataList,
                        measurementTodayByType = _MeasurementTodayByType
                    )
                }
            }
        }
    }

    var _allMeasurements = mutableListOf<MeasurementModel>()
    var _MeasurementTodayByType = mutableMapOf<MeasurementType, MeasurementTodayState>()


    fun addNewMeasurement(measurementModel: MeasurementModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.addNewMeasurement(measurementModel = measurementModel)
        }
    }

    fun getLast7DataByType(measurementType: MeasurementType): List<ColumnarData> {
        val columnarDataList = mutableListOf<ColumnarData>()
        // initialize the list with 7 empty values
        for (i in 0..6) {
            columnarDataList.add(ColumnarData(0f, ""))
        }

        if (_allMeasurements.isNotEmpty()) {
            val measurementModels = _allMeasurements.filter {
                it.measurementType == measurementType
            }.sortedByDescending { // the bigger, the more recent, [today, yesterday, 2d ago...]
                it.startAt
            }.take(7)
            // store today (the last)
            // size safety
            measurementModels.forEach {
                columnarDataList.add(
                    ColumnarData(
                        it.value, ""
                    )
                )
            }
            val todayVal = if (measurementModels.isNotEmpty()) measurementModels.first().value else 0.0
            _MeasurementTodayByType.put(
                measurementType,
                MeasurementTodayState(
                    measurementType = measurementType,
                    value = todayVal.toString(),
                    unit = measurementType.getUnitString()
                )
            )

            // find the max value in the list and normalize the values
            val maxValue = columnarDataList.maxOf { it.value } // seconds
            columnarDataList.map {
                it.value = it.value / maxValue
            }
        } else {
            _MeasurementTodayByType.put(
                measurementType,
                MeasurementTodayState(
                    measurementType = measurementType,
                    value = "0",
                    unit = measurementType.getUnitString()
                )
            )
        }

        return columnarDataList.reversed()
    }


    private fun getLast7ColumnarData(): MutableMap<MeasurementType, List<ColumnarData>> {
        val columnarDataList = mutableMapOf<MeasurementType, List<ColumnarData>>()
        MeasurementType.values().map {
            val columnarData = getLast7DataByType(it)
            columnarDataList.put(it, columnarData)
        }
        return columnarDataList
    }


}

