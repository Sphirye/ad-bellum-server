package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.Authority
import com.sphirye.springtemplate.domain.port.outbound.AuthorityPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.AuthorityRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AuthorityPersistenceAdapter(
    private val authorityRepository: AuthorityRepository,
) : AuthorityPersistencePort {

    override fun findById(id: Long): Authority? =
        authorityRepository.findById(id).map { it.toDomain() }.orElse(null)

    override fun findByRole(role: Authority.Role): Authority? =
        authorityRepository.findByRole(com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Authority.Role.valueOf(role.name)).toDomain()

    override fun existsByRole(role: Authority.Role): Boolean =
        authorityRepository.existsByRole(com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Authority.Role.valueOf(role.name))

    @Transactional
    override fun save(authority: Authority): Authority =
        authorityRepository.save(authority.toEntity()).toDomain()
}
