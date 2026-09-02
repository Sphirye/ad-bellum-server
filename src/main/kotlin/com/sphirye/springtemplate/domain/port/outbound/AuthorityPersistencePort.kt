package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.Authority

interface AuthorityPersistencePort {
    fun findById(id: Long): Authority?
    fun findByRole(role: Authority.Role): Authority?
    fun existsByRole(role: Authority.Role): Boolean
    fun save(authority: Authority): Authority
}
