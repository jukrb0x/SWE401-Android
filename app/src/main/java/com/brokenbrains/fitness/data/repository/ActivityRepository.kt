package com.brokenbrains.fitness.data.repository

import com.brokenbrains.fitness.data.dao.ActivityDao
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import com.brokenbrains.fitness.data.model.activity.ActivityType
import com.brokenbrains.fitness.data.util.CalendarUtils
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val activityDao: ActivityDao
) {

    fun addNewActivity(
        activityModel: ActivityModel

    ) {
        activityDao.insert(activityModel)
    }

    suspend fun getActivityById(id: Int): Flow<ActivityModel> {
        return activityDao.getById(id)
    }

    fun getAllActivities(): Flow<List<ActivityModel>> {
        return activityDao.getAll()
    }

    fun getActivityByType(type: ActivityType): List<ActivityModel> {
        return activityDao.getByType(type)
    }


    // get last 7 days of some type
    // convert to graph values (.asColumnarData())
    fun getLast7DaysOfActivityByTypes(types: List<ActivityType>): MutableList<ColumnarData>
    {
        val activities = mutableListOf<ActivityModel>()
        types.forEach { type ->
            activities.addAll(activityDao.getByType(type))
        }
        // get current time
        val now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        val last7DaysModels =
            activities.filter { it.startAt!! > now - 7 * 24 * 60 * 60 * 1000 }
        val last7DaysAsColumnarData = mutableListOf<ColumnarData>();
        for (day in CalendarUtils.getLast7Days()) {
            val models =
                last7DaysModels.filter { it.startAt!! > now - day.number * 24 * 60 * 60 * 1000 }
            val total = models.sumOf { it.endAt!! - it.startAt!! }.toFloat()
            last7DaysAsColumnarData.add(ColumnarData(label = day.label, value = total))
        }
        return last7DaysAsColumnarData
    }

}