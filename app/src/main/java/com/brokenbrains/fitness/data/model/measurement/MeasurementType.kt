package com.brokenbrains.fitness.data.model.measurement

enum class ActivityType {
    HEIGHT,
    WEIGHT;

    companion object {
        fun fromString(type: String): ActivityType {
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

fun ActivityType.toReadableString(): String {
    return when (this) {
        ActivityType.HEIGHT -> "Height"
        ActivityType.WEIGHT -> "Weight"
    }
}