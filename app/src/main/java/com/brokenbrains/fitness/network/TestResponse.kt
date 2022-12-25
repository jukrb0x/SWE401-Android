package com.brokenbrains.fitness.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class TestResponse(
    @Json(name = "date") val date: Date,
    @Json(name = "message") val message: String
)