package com.rae.utibuhealth.data.model

data class LoginResponse(
    val status: String,
    val message: String,
    val payload: Payload
)

data class Payload(
    val token: String,
    val name: String,
    val email: String,
    val role: String
)
data class RegisterResponse(
    val status: String,
    val message: String,

)
