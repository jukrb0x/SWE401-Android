package com.brokenbrains.fitness.model.activity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "activity")
class ActivityModel {
    var id: Int? = null
    var name: String? = null
    var startAt: Date? = null
    var duration: Int? = null // TODO: type is subject to change
    var activityType: ActivityType? = ActivityType.OTHER
}