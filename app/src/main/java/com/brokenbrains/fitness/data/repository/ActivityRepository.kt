package com.brokenbrains.fitness.data.repository

import com.brokenbrains.fitness.data.dao.ActivityDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val activityDao: ActivityDao
) {



}