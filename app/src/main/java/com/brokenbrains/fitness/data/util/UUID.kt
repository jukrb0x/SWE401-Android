package com.brokenbrains.fitness.data.util

class UUID {
    companion object {
        fun generateUUID(): String {
            return java.util.UUID.randomUUID().toString()
        }
    }
}