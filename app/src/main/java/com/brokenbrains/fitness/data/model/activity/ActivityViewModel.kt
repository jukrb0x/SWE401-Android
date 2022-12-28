package com.brokenbrains.fitness.data.model.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brokenbrains.fitness.data.repository.ActivityRepository
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
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

//    var _allActivities = MutableStateFlow(listOf<ActivityModel>())

    //    val allActivities: StateFlow<List<ActivityModel>>
//        get() = _allActivities
//    var _allActivities = emptyList<ActivityModel>();
    val allActivities = repository.getAllActivities()
//      get() = {
//        viewModelScope.launch(Dispatchers.IO) {
//          _allActivities = repository.getAllActivities()
//        }
//        _allActivities
//      }

//    init{
//        getAllActivities()
//    }
//    fun getAllActivities() {
//        viewModelScope.launch {
//            repository.getAllActivities().catch { e ->
//                e.printStackTrace()
//            }.collect {
//                _allActivities.value = it
//            }
//        }
//    }


    fun addNewActivity(activityModel: ActivityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            if(activityModel.startAt!! < activityModel.endAt!!){
                repository.addNewActivity(activityModel = activityModel)
            }
        }
    }

    fun getLast7DaysColumnarDataByType(activityType: ActivityType): List<ColumnarData> {
        val columnarDataList = mutableListOf<ColumnarData>()
        val dayOfWeeks = CalendarUtils.getLast7Days()
        if (allActivities.isNotEmpty()) {
            val models =
                allActivities.filter { it.activityType == activityType }.sortedBy { it.startAt }
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

    fun getTodayTrendCardValueAndUnitByType(activityType: ActivityType) {

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