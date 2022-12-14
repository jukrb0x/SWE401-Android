package com.brokenbrains.fitness.data.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brokenbrains.fitness.data.util.UUID

@Entity(tableName = "medication")
data class MedicationModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "uuid")
    var uuid: String = UUID.generateUUID(),

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "start_at")
    var startAt: Long? = System.currentTimeMillis(), // intake time of day

    @ColumnInfo(name = "dayofweek")
    var daysOfWeek: String? = null, // 1-7, "[1,2,3]" means monday, tuesday, wednesday

    @ColumnInfo(name = "dose")
    var dose: String? = null,

    @ColumnInfo(name = "medication_type")
    var medicationType: MedicationType? = MedicationType.OTHER


)
