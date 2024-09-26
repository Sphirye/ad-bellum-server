package com.sphirye.springtemplate.security

import com.sphirye.springtemplate.model.UserIdentity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SessionManager {

    fun getUserIdentity(): UserIdentity {
        return SecurityContextHolder.getContext().authentication.principal as UserIdentity
    }

    fun isAuthenticated(): Boolean {
        return if (_getAuthentication() == null) {
            false
        } else {
            _getAuthentication()?.principal is UserIdentity
        }
    }

    fun requestingUserMatchesWithSessionId(id: Long): Boolean {
        return id == getUserIdentity().id
    }

    private fun _getAuthentication(): Authentication? {
        return SecurityContextHolder.getContext().authentication
    }

}