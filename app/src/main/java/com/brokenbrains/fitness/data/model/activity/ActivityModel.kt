package com.brokenbrains.fitness.data.model.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "activity")
data class ActivityModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "start_at")
    // store in string
    var startAt: String? = null,

    @ColumnInfo(name = "end_at")
    var endAt: String? = null,

    @ColumnInfo(name = "activity_type")
    var activityType: ActivityType? = ActivityType.OTHER

)

// as xxxx