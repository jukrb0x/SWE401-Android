package com.brokenbrains.fitness.network.response

data class UserResponse(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val age: Int?,
    val weight: Int?,
    val height: Int?,
    val bloodType: String?
)