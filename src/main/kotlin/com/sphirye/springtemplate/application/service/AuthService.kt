package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.application.dto.Session
import com.sphirye.springtemplate.domain.model.UserIdentity
import com.sphirye.springtemplate.domain.port.inbound.AuthUseCase
import com.sphirye.springtemplate.domain.port.outbound.CurrentSessionPort
import com.sphirye.springtemplate.domain.port.outbound.PasswordEncoderPort
import com.sphirye.springtemplate.domain.port.outbound.TokenGeneratorPort
import com.sphirye.springtemplate.domain.port.outbound.UserPersistencePort
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userPersistencePort: UserPersistencePort,
    private val passwordEncoderPort: PasswordEncoderPort,
    private val tokenGeneratorPort: TokenGeneratorPort,
    private val currentSessionPort: CurrentSessionPort,
) : AuthUseCase {

    override fun login(email: String, password: String): Session {
        val user = userPersistencePort.findByEmail(email)
            ?: throw ResourceNotFoundException("Email '$email' not found")

        if (!passwordEncoderPort.matches(password, user.password.orEmpty())) {
            throw BadCredentialsException("Bad credentials")
        }

        return Session(
            token = "Bearer ${tokenGeneratorPort.generateAccessToken(user.id!!)}",
            user = user,
        )
    }

    override fun getRenewedSession(): Session {
        val userIdentity: UserIdentity = currentSessionPort.getUserIdentity()
        val user = userPersistencePort.findById(userIdentity.id)
            ?: throw ResourceNotFoundException("Resource with id ${userIdentity.id} does not exists")

        return Session(
            token = "Bearer ${tokenGeneratorPort.generateAccessToken(user.id!!)}",
            user = user,
        )
    }
}
