package com.sphirye.springtemplate.service

import com.sphirye.springtemplate.model.Session
import com.sphirye.springtemplate.model.UserCredentials
import com.sphirye.springtemplate.model.UserIdentity
import com.sphirye.springtemplate.security.SessionManager
import com.sphirye.springtemplate.security.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    private lateinit var _userService: UserService

    @Autowired
    private lateinit var _authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var _passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var _jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var _sessionManager: SessionManager

    fun login(credentials: UserCredentials): Session {
        val user = _userService.findByEmail(credentials.email)

        if (!_passwordEncoder.matches(credentials.password, user.password)) {
            throw BadCredentialsException("Bad credentials")
        }

        val userIdentity = UserIdentity(email = user.email!!, user.id!!)

        val auth = _authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userIdentity, user.password, listOf())
        )

        return Session(
            token = "Bearer ${_jwtTokenUtil.generateAccessToken(userIdentity.id)}",
            user = user
        )
    }

    fun getRenewedSession(): Session {
        val userIdentity = _sessionManager.getUserIdentity()
        val user = _userService.findById(userIdentity.id)

        return Session(
            token = "Bearer ${_jwtTokenUtil.generateAccessToken(userIdentity.id)}",
            user = user
        )
    }
}