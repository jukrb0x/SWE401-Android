package com.brokenbrains.fitness.data.model.measurement

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.MeasurementRepository
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MeasurementTodayState(
    val MeasurementType: MeasurementType,
    val value: String,
    val unit: String,
)

data class MeasurementUiState(
    var allMeasurements: List<MeasurementModel> = listOf(),
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

    suspend fun refresh() {
        viewModelScope.launch {
            repository.getAllMeasurements().flowOn(Dispatchers.IO).collect {
                _allMeasurements = it.toMutableList()
                val columnarDataList = getLast7ColumnarData()
                _uiState.update {
                    it.copy(
                        allMeasurements = _allMeasurements,
                        MeasurementColumnarDataByType = columnarDataList,
                        MeasurementTodayByType = _MeasurementTodayByType
                    )
                }
            }
        }
    }

    var _allMeasurements = mutableListOf<MeasurementModel>()
    var _MeasurementTodayByType = mutableMapOf<MeasurementType, MeasurementTodayState>()


    fun addNewMeasurement(MeasurementModel: MeasurementModel) {
        CoroutineScope(Dispatchers.IO).launch {
//            if (MeasurementModel.startAt!! < MeasurementModel.endAt!!) {
                repository.addNewMeasurement(measurementModel = MeasurementModel)
//            }
        }
    }

    fun getLast7DataByType(MeasurementType: MeasurementType): List<MeasurementModel> {
        return _allMeasurements.filter {
            it.measurementType == MeasurementType
        }.sortedByDescending {
            it.startAt
        }.take(7)
    }


    private fun getLast7ColumnarData(): MutableMap<MeasurementType, List<ColumnarData>> {
        val columnarDataList = mutableMapOf<MeasurementType, List<ColumnarData>>()
        MeasurementType.values().map {
            val columnarData = getLast7DataByType(it)
            columnarDataList.put(it, columnarData.map {
                ColumnarData(
                    it.value,
                    ""
                )
            })
        }
        return columnarDataList
    }


}

