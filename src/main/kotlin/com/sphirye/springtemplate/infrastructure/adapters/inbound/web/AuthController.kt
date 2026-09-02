package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.application.dto.Session
import com.sphirye.springtemplate.domain.port.inbound.AuthUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.LoginRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authUseCase: AuthUseCase,
) {

    @PostMapping("/auth/login")
    fun postLogin(@RequestBody credentials: LoginRequest): Session {
        return authUseCase.login(credentials.email, credentials.password)
    }

    @GetMapping("/auth/check")
    fun checkAuth(): Session {
        return authUseCase.getRenewedSession()
    }

}
