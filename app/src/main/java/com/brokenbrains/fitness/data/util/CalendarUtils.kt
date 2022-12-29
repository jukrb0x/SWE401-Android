package com.brokenbrains.fitness.data.util

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
        SUNDAY(7, "S")
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

}