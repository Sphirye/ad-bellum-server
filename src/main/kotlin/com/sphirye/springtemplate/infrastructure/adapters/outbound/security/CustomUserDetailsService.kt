package com.sphirye.springtemplate.infrastructure.adapters.outbound.security

import com.sphirye.springtemplate.domain.port.outbound.UserPersistencePort
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component("userDetailsService")
class CustomUserDetailsService(
    private val userPersistencePort: UserPersistencePort,
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userPersistencePort.findByEmail(email)
            ?: throw UsernameNotFoundException("Email '$email' not found")
        return _createUserDetails(user)
    }

    private fun _createUserDetails(user: com.sphirye.springtemplate.domain.model.User): User {
        return User(user.email, user.password, user.authorities.toGrantedAuthorities())
    }
}
