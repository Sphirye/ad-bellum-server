package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository

import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<Authority, Long> {

    fun existsByRole(role: Authority.Role): Boolean

    fun findByRole(role: Authority.Role): Authority

}
