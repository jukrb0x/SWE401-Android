package com.brokenbrains.fitness.data.model.medication

import com.brokenbrains.fitness.data.model.measurement.MeasurementType


enum class MedicationType {
    PILLS,
    INJECTION,
    LIQUID,
    OTHER;

    companion object {
        fun fromString(type: String):MedicationType {
            return when (type) {
                "PILLS" -> PILLS
                "INJECTION" -> INJECTION
                "LIQUID" -> LIQUID
                "OTHER" -> OTHER
                else -> OTHER
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

fun MedicationType.toReadableString(): String {
    return when (this) {
        MedicationType.PILLS -> "Pills"
        MedicationType.INJECTION -> "Injection"
        MedicationType.LIQUID -> "Liquid"
        MedicationType.OTHER -> "Other"
    }
}

fun MedicationType.getUnitString(): String {
    return when (this) {
        MedicationType.PILLS -> "Pills"
        MedicationType.INJECTION -> "ml"
        MedicationType.LIQUID -> "ml"
        MedicationType.OTHER -> "Other"
    }
}