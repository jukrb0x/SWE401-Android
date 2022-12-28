package com.brokenbrains.fitness.data.util

import java.time.LocalDate
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

}