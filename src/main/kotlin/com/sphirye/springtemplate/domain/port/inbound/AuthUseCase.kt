package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.application.dto.Session

interface AuthUseCase {
    fun login(email: String, password: String): Session
    fun getRenewedSession(): Session
}
