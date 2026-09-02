package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.UserIdentity

interface CurrentSessionPort {
    fun getUserIdentity(): UserIdentity
    fun isAuthenticated(): Boolean
    fun requestingUserMatchesWithSessionId(id: Long): Boolean
}
