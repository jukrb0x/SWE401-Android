package com.brokenbrains.fitness.data.model.measurement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measurement")
data class MeasurementModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "value")
    var value: Float = 0f,

    @ColumnInfo(name = "start_at")
    var startAt: Long? = System.currentTimeMillis(), // record time

    @ColumnInfo(name = "measurement_type")
    var measurementType: MeasurementType? = MeasurementType.WEIGHT
)