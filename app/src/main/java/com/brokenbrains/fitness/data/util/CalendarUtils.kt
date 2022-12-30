package com.brokenbrains.fitness.data.util

import com.brokenbrains.fitness.data.model.measurement.MeasurementType
import com.brokenbrains.fitness.data.model.measurement.toReadableString
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Singleton

@Singleton
object CalendarUtils {

    enum class DayOfWeek(val number: Int, val label: String) {
        MONDAY(1, "M"),
        TUESDAY(2, "T"),
        WEDNESDAY(3, "W"),
        THURSDAY(4, "T"),
        FRIDAY(5, "F"),
        SATURDAY(6, "S"),
        SUNDAY(7, "S");

        companion object {
            fun fromString(label: String): DayOfWeek {
                return when (label) {
                    "MONDAY" -> MONDAY
                    "TUESDAY" -> TUESDAY
                    "WEDNESDAY" -> WEDNESDAY
                    "THURSDAY" -> THURSDAY
                    "FRIDAY" -> FRIDAY
                    "SATURDAY" -> SATURDAY
                    "SUNDAY" -> SUNDAY
                    else -> MONDAY
                }
            }

            fun getStringList(): List<String> {
                val strList = mutableListOf<String>()
                for (type in DayOfWeek.values()) {
                    strList.add(type.toReadableString())
                }
                return strList

            }
        }
    }

    fun DayOfWeek.toReadableString(): String {
        return when (this) {
            DayOfWeek.MONDAY -> "Monday"
            DayOfWeek.TUESDAY -> "Tuesday"
            DayOfWeek.WEDNESDAY -> "Wednesday"
            DayOfWeek.THURSDAY -> "Thursday"
            DayOfWeek.FRIDAY -> "Friday"
            DayOfWeek.SATURDAY -> "Saturday"
            DayOfWeek.SUNDAY -> "Sunday"
        }
    }

    fun DayOfWeek.toReadableStringShort(): String {
        return when (this) {
            DayOfWeek.MONDAY -> "Mon"
            DayOfWeek.TUESDAY -> "Tue"
            DayOfWeek.WEDNESDAY -> "Wed"
            DayOfWeek.THURSDAY -> "Thu"
            DayOfWeek.FRIDAY -> "Fri"
            DayOfWeek.SATURDAY -> "Sat"
            DayOfWeek.SUNDAY -> "Sun"
        }
    }

    val today: Int = LocalDate.now().getDayOfWeek().getValue()

    /**
     * Returns DayOfWeek ordinal list with today as last element
     */
    fun getLast7Days(): MutableList<DayOfWeek> {
        val list = mutableListOf<DayOfWeek>()
        for (i in 0..6) {
            list.add(
                DayOfWeek.values()[Math.floorMod(((today - 1) - i), 7)]
            )
        }
        return list.asReversed()
    }

    fun getDateTimeFromLong(l: Long): LocalDateTime? {
        val dt = Instant.ofEpochSecond(l)
            .atZone(ZoneId.of("UTC"))
            .toLocalDateTime()
        return dt
    }

    fun getDayOfWeekFromLong(l: Long): DayOfWeek {
        val dt = Instant.ofEpochSecond(l)
            .atZone(ZoneId.of("UTC"))
            .toLocalDateTime()
        return DayOfWeek.values()[dt.dayOfWeek.value - 1]
    }

    fun isSameDay(day: DayOfWeek, startAt: Long?): Boolean {
        val dt = Instant.ofEpochSecond(startAt ?: 0)
            .atZone(/*ZoneId.systemDefault()*/ZoneId.of("UTC")) // we store the dt in UTC, the comparison should be done in UTC
            .toLocalDateTime()
        return dt.dayOfWeek.value == day.number
    }


    data class BiggestUnitStringOfTime(
        val value: String,
        val unit: String
    )

    fun getBiggestUnitStringOfTime(l: Long): BiggestUnitStringOfTime {
        val hours = l / 3600
        val mid = Math.floorMod(l, 3600)
        val minutes = mid / 60
        val seconds = Math.floorMod(mid, 60)
        return if (hours > 0) {
            BiggestUnitStringOfTime(hours.toString(), "hr")
        } else if (minutes > 0) {
            BiggestUnitStringOfTime(minutes.toString(), "min")
        } else {
            BiggestUnitStringOfTime(seconds.toString(), "sec")
        }
    }

    fun getListFromDayOfWeekString(str: String): List<DayOfWeek> {
        val onlyStr = str.substring(1, str.length - 1)  // remove the brackets
        val newList = mutableListOf<DayOfWeek>()
        val list = onlyStr.split(",")
        for (i in list) {
            newList.add(DayOfWeek.fromString(i.trim()))
        }
        return newList
    }

}