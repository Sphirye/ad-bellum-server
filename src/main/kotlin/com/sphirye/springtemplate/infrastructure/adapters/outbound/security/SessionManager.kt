package com.sphirye.springtemplate.infrastructure.adapters.outbound.security

import com.sphirye.springtemplate.domain.model.UserIdentity
import com.sphirye.springtemplate.domain.port.outbound.CurrentSessionPort
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SessionManager : CurrentSessionPort {

    override fun getUserIdentity(): UserIdentity {
        return SecurityContextHolder.getContext().authentication.principal as UserIdentity
    }

    override fun isAuthenticated(): Boolean {
        return if (_getAuthentication() == null) {
            false
        } else {
            _getAuthentication()?.principal is UserIdentity
        }
    }

    override fun requestingUserMatchesWithSessionId(id: Long): Boolean {
        return id == getUserIdentity().id
    }

    private fun _getAuthentication(): Authentication? {
        return SecurityContextHolder.getContext().authentication
    }

}
