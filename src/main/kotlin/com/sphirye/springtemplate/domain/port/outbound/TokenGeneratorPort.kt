package com.sphirye.springtemplate.domain.port.outbound

interface TokenGeneratorPort {
    fun generateAccessToken(userId: Long): String
    fun validate(token: String)
    fun resolveSubject(token: String): String
}
