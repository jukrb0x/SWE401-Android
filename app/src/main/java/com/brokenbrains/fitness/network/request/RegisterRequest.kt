package com.brokenbrains.fitness.network.request

data class RegisterRequest (
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
)