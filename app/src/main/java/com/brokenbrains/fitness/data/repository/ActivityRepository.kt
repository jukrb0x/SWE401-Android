package com.brokenbrains.fitness.data.repository

import com.brokenbrains.fitness.data.dao.ActivityDao
import com.brokenbrains.fitness.data.model.activity.ActivityModel
import kotlinx.coroutines.flow.Flow
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


}