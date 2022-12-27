package com.brokenbrains.fitness.data

import androidx.room.TypeConverter
import java.text.ParseException
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return if (value != null) {
            try {
                return Date(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.time
    }

}