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
            val strList = mutableListOf<String>()
            for (type in values()) {
                strList.add(type.toReadableString())
            }
            return strList
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

// avoid using this, because it may be larger than 60 min -> 1 h
fun ActivityType.getUnit(): String {
    return when (this) {
        ActivityType.WALKING -> "min"
        ActivityType.RUNNING -> "min"
        ActivityType.CYCLING -> "min"
        ActivityType.SWIMMING -> "min"
        else -> "min"
    }
}
