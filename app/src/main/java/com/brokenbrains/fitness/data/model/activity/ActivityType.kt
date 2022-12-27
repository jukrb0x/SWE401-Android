package com.brokenbrains.fitness.data.model.activity

enum class ActivityType {
    WALKING,
    RUNNING,
    CYCLING,
    SWIMMING,
    OTHER;

    companion object {
        fun fromString(type: String): ActivityType {
            return when (type) {
                "WALKING" -> WALKING
                "RUNNING" -> RUNNING
                "CYCLING" -> CYCLING
                "SWIMMING" -> SWIMMING
                else -> OTHER
            }
        }

        fun getStringList(): List<String> {
            return listOf(
                WALKING.toReadableString(),
                RUNNING.toReadableString(),
                CYCLING.toReadableString(),
                SWIMMING.toReadableString(),
                OTHER.toReadableString()
            )
        }
    }
}

fun ActivityType.toReadableString(): String {
    return when (this) {
        ActivityType.WALKING -> "Walking"
        ActivityType.RUNNING -> "Running"
        ActivityType.CYCLING -> "Cycling"
        ActivityType.SWIMMING -> "Swimming"
        ActivityType.OTHER -> "Other"
    }
}