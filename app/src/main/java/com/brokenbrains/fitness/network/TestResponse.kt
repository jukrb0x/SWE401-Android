package com.brokenbrains.fitness.network

import com.squareup.moshi.Json
import java.util.*

data class TestResponse(
    @Json(name = "date") val date: Date,
    @Json(name = "message") val message: String
)