package com.brokenbrains.fitness.data.model.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brokenbrains.fitness.ui.components.trendcard.ColumnarData

@Entity(tableName = "activity")
data class ActivityModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "start_at")
    // store in string
    var startAt: Long? = null,

    @ColumnInfo(name = "end_at")
    var endAt: Long? = null,

    @ColumnInfo(name = "activity_type")
    var activityType: ActivityType? = ActivityType.OTHER

)

data class Duration(
    var hours: Int,
    var minutes: Int,
    var seconds: Int
)

fun ActivityModel.getDuration(): Duration {
    val start = startAt
    val end = endAt
    var hours: Long = 0L
    var minutes: Long = 0L
    var seconds: Long = 0L
    if (start != null && end != null) {
        val duration = end - start
        hours = duration / 3600
        minutes = (duration % 3600) / 60 // Math.floorMod(duration, 3600) / 60
        seconds = ((duration % 3600) % 60)
        println("$hours:$minutes:$seconds")
    }
    return Duration(hours.toInt(), minutes.toInt(), seconds.toInt())
}

fun ActivityModel.duration(): Long {
    val start = startAt
    val end = endAt
    if (start != null && end != null) {
        return end - start
    } else {
        return 0
    }
}
// as xxxx

fun List<ActivityModel>.asColumnarData(): List<ColumnarData> {
    val graphValues = mutableListOf<ColumnarData>()
    for (activity in this) {
        val duration = activity.endAt?.minus(activity.startAt ?: 0) ?: 0
        graphValues.add(
            ColumnarData(
                value = duration.toFloat()
                /*Instant.ofEpochSecond(duration).atZone(ZoneId.systemDefault()).toLocalTime().format(
                    DateTimeFormatterBuilder()
                        .appendPattern("HH:mm")
                        .toFormatter(Locale.getDefault())
                ),
                */, label = activity.title ?: "No title"
            )
        )
    }
    return graphValues
}