package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

data class LoginRequest(
    val email: String,
    val password: String,
)
