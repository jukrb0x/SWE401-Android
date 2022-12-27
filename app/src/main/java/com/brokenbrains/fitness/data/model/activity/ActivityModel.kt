package com.brokenbrains.fitness.data.model.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(tableName = "activity")
data class ActivityModel (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "data")
    var data: String? = null,

    @ColumnInfo(name = "start_at")
    var startAt: Date? = null,

    @ColumnInfo(name = "end_at")
    var duration: Int? = null,

    @ColumnInfo(name = "activity_type")
    var activityType: ActivityType? = ActivityType.OTHER
)