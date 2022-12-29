package com.brokenbrains.fitness.data.model.measurement

import com.brokenbrains.fitness.data.model.activity.ActivityType

enum class MeasurementType {
    HEIGHT,
    WEIGHT;

    companion object {
        fun fromString(type: String): MeasurementType {
            return when (type) {
                "HEIGHT" -> HEIGHT
                "WEIGHT" -> WEIGHT
                else -> HEIGHT
            }
        }

        fun getStringList(): List<String> {
            val strList = mutableListOf<String>()
            for (type in values()) {
                strList.add(type.toReadableString())
            }
            return strList

        }
    }
}

fun MeasurementType.toReadableString(): String {
    return when (this) {
        MeasurementType.HEIGHT -> "Height"
        MeasurementType.WEIGHT -> "Weight"
    }
}