package com.sphirye.springtemplate.infrastructure.adapters.outbound.security

import com.sphirye.springtemplate.domain.model.UserIdentity
import com.sphirye.springtemplate.domain.port.outbound.UserPersistencePort
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtTokenFilter(
    private val _jwtTokenUtil: JwtTokenUtil,
    private val _userPersistencePort: UserPersistencePort,
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val token = _resolveToken(request)

        if (token == null) {
            chain.doFilter(request, response)
            return
        }

        _jwtTokenUtil.validate(token)

        val user = _userPersistencePort.findById(_jwtTokenUtil.resolveSubject(token).toLong())
            ?: run {
                chain.doFilter(request, response)
                return
            }

        val auth = UsernamePasswordAuthenticationToken(
            UserIdentity(user.email!!, user.id!!),
            user.password,
            user.authorities.toGrantedAuthorities()
        )

        auth.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = auth

        chain.doFilter(request, response)
    }

    private fun _resolveToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        if (authHeader.isNullOrEmpty()) {
            return null
        }

        return authHeader
            .split(" ".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()[1]
            .trim { it <= ' ' }
    }
}
