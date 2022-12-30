package com.brokenbrains.fitness.data.model.medication

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brokenbrains.fitness.data.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//data class MeasurementTodayState(
//    val measurementType: MeasurementType,
//    val value: String,
//    val unit: String,
//)

data class MedicationUiState(
    var allMedications: List<MedicationModel> = listOf(),
)

@HiltViewModel
class MedicationViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MedicationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MedicationUiState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            refresh()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.getAllMedications().flowOn(Dispatchers.IO).collect {
                _allMedications = it.toMutableList()
                _uiState.update {
                    it.copy(
                        allMedications = _allMedications
                    )
                }
            }
        }
    }

    var _allMedications = mutableListOf<MedicationModel>()


    fun addNewMedication(medication: MedicationModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.addNewMedication(medication)
        }
    }

    fun deleteMedication(medication: MedicationModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteMedication(medication.uuid)
        }
    }
//
//    fun getMostRecentMedication(): MedicationModel {
//        //        medication has a
//
//    }

/*

    fun getLast7DataByType(measurementType: MeasurementType): List<ColumnarData> {
        val columnarDataList = mutableListOf<ColumnarData>()
        if (_allMeasurements.isNotEmpty()) {
            val measurementModels = _allMeasurements.filter {
                it.measurementType == measurementType
            }.sortedBy { // the bigger, the more recent, [today, yesterday, 2d ago...]
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
            // padding to left
            if (columnarDataList.size < 7) {
                columnarDataList.reverse()
                for (i in 0..6) {
                    if (columnarDataList.size < 7) {
                        columnarDataList.add(ColumnarData(0f, ""))
                    }
                }
                columnarDataList.reverse()
            }
            val todayVal =
                if (measurementModels.isNotEmpty()) measurementModels.last().value else 0.0
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

        return columnarDataList
    }


    private fun getLast7ColumnarData(): MutableMap<MeasurementType, List<ColumnarData>> {
        val columnarDataList = mutableMapOf<MeasurementType, List<ColumnarData>>()
        MeasurementType.values().map {
            val columnarData = getLast7DataByType(it)
            columnarDataList.put(it, columnarData)
        }
        return columnarDataList
    }

*/

}

